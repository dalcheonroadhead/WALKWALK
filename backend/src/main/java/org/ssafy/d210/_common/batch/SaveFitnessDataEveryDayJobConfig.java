package org.ssafy.d210._common.batch;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.service.MemberDataService;
import org.ssafy.d210.walk.dto.response.FitnessResponse;
import org.ssafy.d210.walk.entity.Exercise;
import org.ssafy.d210.walk.repository.ExerciseRepository;
import org.ssafy.d210.walk.service.ExerciseService;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Configuration
@RequiredArgsConstructor
public class SaveFitnessDataEveryDayJobConfig extends DefaultBatchConfiguration {

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
        return new StepBuilder("Step1GetRefreshToken", jobRepository)
                .<UserDetailsImpl, Exercise>chunk(10, platformTransactionManager)
                .reader(userDetailsItemReader(entityManagerFactory))
                .processor(accessTokenProcessor())
                .writer(userDetailsItemWriter())
                .build();
    }

    @Bean
    public JpaPagingItemReader<UserDetailsImpl> userDetailsItemReader(EntityManagerFactory entityManagerFactory) {
        JpaPagingItemReader<UserDetailsImpl> reader = new JpaPagingItemReader<>();
        reader.setQueryString("select m from members m");
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(10);
        return reader;
    }

    @Bean
    public ItemProcessor<UserDetailsImpl, Exercise> accessTokenProcessor() {
        return userDetail -> {
            // access token 받아오기
            String accessToken = memberDataService.refreshAccessToken(userDetail);

            // 받아온 액세스 토큰으로 구글 피트니스 데이터 가져오기
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startOfYesterday = now.minusDays(1).toLocalDate().atStartOfDay();
            LocalDateTime endOfYesterday = startOfYesterday.plusDays(1).minusNanos(1);

            long startTimeMillis = startOfYesterday.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long endTimeMillis = endOfYesterday.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

            FitnessResponse fitnessResponse = exerciseService.fetchGoogleFitData(accessToken, startTimeMillis, endTimeMillis);

            return exerciseService.mapFitnessResponseToExercise(fitnessResponse, userDetail.getMember());
        };
    }

    @Bean
    public ItemWriter<Exercise> userDetailsItemWriter() {
        return exercises -> {
            for (Exercise exercise : exercises) {
                exerciseRepository.save(exercise);
            }
        };
    }


}
