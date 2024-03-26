package org.ssafy.d210._common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final JobExplorer jobExplorer;

    @Scheduled(cron = "0 58 3 * * *")
    public void runBatchJob() {
        Set<JobExecution> executions = jobExplorer.findRunningJobExecutions(job.getName());
        if (!executions.isEmpty()) {
            log.info("이전 작업이 아직 실행 중입니다. 새 작업을 시작하지 않습니다.");
            return;
        }

        try {
            // 현재 시간을 JobParameters에 추가, 중단됐던 job 실행할 때 활용
            JobParameters parameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(job, parameters);
        } catch (Exception e) {
            log.error("배치 작업 실행 중 에러가 발생했습니다.", e);
        }
    }
}

