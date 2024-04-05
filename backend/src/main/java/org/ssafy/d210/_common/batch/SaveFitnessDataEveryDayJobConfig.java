package org.ssafy.d210._common.batch;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.service.MemberDataService;
import org.ssafy.d210.walk.dto.response.FitnessResponse;
import org.ssafy.d210.walk.entity.Exercise;
import org.ssafy.d210.walk.repository.ExerciseRepository;
import org.ssafy.d210.walk.service.ExerciseService;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SaveFitnessDataEveryDayJobConfig {

    private final EntityManagerFactory entityManagerFactory;
    private final MemberDataService memberDataService;
    private final ExerciseService exerciseService;
    private final ExerciseRepository exerciseRepository;

    @Bean
    public Job saveFitnessDataEveryDayJob(JobRepository jobRepository, Step saveFitnessDataEveryDayStep) {

        return new JobBuilder("saveFitnessDataEveryDayJob", jobRepository)
                .start(saveFitnessDataEveryDayStep)
                .build();
    }

    @Bean
    public Step saveFitnessDataEveryDayStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {

        return new StepBuilder("saveFitnessDataEveryDayStep", jobRepository)
                .<Members, Exercise>chunk(10, platformTransactionManager)
                .reader(userDetailsItemReader(entityManagerFactory))
                .processor(accessTokenProcessor(null))
                .writer(userDetailsItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Members> userDetailsItemReader(EntityManagerFactory entityManagerFactory) {

        log.info("save job itemReader 진입 성공");
        return new JpaPagingItemReaderBuilder<Members>()
                .name("userDetailsItemReader")
                .queryString("SELECT m FROM Members m")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Members, Exercise> accessTokenProcessor(@Value("#{jobParameters['time']}") Long timeParam) {
        return member -> {

            log.info("save job processor 진입 성공 " + member.getNickname());

            try {
                // access token 받아오기
                String accessToken = memberDataService.refreshAccessToken(member);

                // 받아온 액세스 토큰으로 구글 피트니스 데이터 가져오기
//                LocalDateTime now = LocalDateTime.now();
                LocalDateTime jobTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeParam), ZoneId.systemDefault());
                LocalDateTime startOfYesterday = jobTime.minusDays(1).toLocalDate().atStartOfDay();
                LocalDateTime endOfYesterday = startOfYesterday.plusDays(1).minusNanos(1);

                log.info("jobTime : " + jobTime);

                long startTimeMillis = startOfYesterday.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                long endTimeMillis = endOfYesterday.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

                FitnessResponse fitnessResponse = exerciseService.fetchGoogleFitData(accessToken, startTimeMillis, endTimeMillis);

                return exerciseService.mapFitnessResponseToExercise(fitnessResponse, member, jobTime.toLocalDate());
            } catch (Exception e) {
                log.info(member.getNickname() + " 씨는 에러가 났다.", e);
                return null;
            }

        };
    }

    @Bean
    public ItemWriter<Exercise> userDetailsItemWriter() {
        return exercises -> {
            for (Exercise exercise : exercises) {
                if (exercise != null) {
                    log.info("save job itemWriter 진입 성공 " + exercise.getMember().getNickname());
                    Optional<Exercise> exerciseOptional = exerciseRepository.findExerciseByMemberAndExerciseDay(exercise.getMember(), exercise.getExerciseDay());
                    exerciseOptional.ifPresent(value -> exercise.setId(value.getId())); // 멱등성
                    exerciseRepository.save(exercise);
                }

            }
        };
    }


}
