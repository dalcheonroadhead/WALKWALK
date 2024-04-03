package org.ssafy.d210._common.batch;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.walk.entity.ExerciseCriteria;
import org.ssafy.d210.walk.service.ExerciseCriteriaService;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class UpdateCriteriaEveryYearJobConfig {

    private final EntityManagerFactory entityManagerFactory;
    private final ExerciseCriteriaService exerciseCriteriaService;

    @Bean
    public Job updateCriteriaEveryYearJob(JobRepository jobRepository, Step updateCriteriaEveryYearStep) {
        return new JobBuilder("updateCriteriaEveryYearJob", jobRepository)
                .start(updateCriteriaEveryYearStep)
                .build();
    }

    @Bean
    public Step updateCriteriaEveryYearStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("updateCriteriaEveryYearStep", jobRepository)
                .<Members, ExerciseCriteria>chunk(10, platformTransactionManager)
                .reader(exerciseCriteriaItemReader(entityManagerFactory))
                .processor(exerciseCriteriaItemProcessor())
                .writer(exerciseCriteriaItemWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Members> exerciseCriteriaItemReader(EntityManagerFactory entityManagerFactory) {

        return new JpaPagingItemReaderBuilder<Members>()
                .name("exerciseCriteriaItemReader")
                .queryString("SELECT m FROM Members m")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(10)
                .build();
    }

    @Bean
    public ItemProcessor<Members, ExerciseCriteria> exerciseCriteriaItemProcessor() {
        return member -> {
            try {
                return exerciseCriteriaService.setDefaultExerciseCriteria(member);
            } catch (Exception e) {
                log.error("운동 기준 업데이트 중 에러가 발생했습니다.", e);
                return null;
            }
        };
    }

    @Bean
    public ItemWriter<ExerciseCriteria> exerciseCriteriaItemWriter() {
        return exerciseCriteria -> {

        };
    }
}
