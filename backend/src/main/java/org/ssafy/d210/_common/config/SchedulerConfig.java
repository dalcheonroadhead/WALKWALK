package org.ssafy.d210._common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.members.service.MemberDataService;
import org.ssafy.d210.walk.dto.response.FitnessResponse;
import org.ssafy.d210.walk.service.ExerciseCriteriaService;
import org.ssafy.d210.walk.service.ExerciseService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class SchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job saveFitnessDataEveryDayJob;
    private final Job updateCriteriaEveryYearJob;
    private final JobExplorer jobExplorer;

    public SchedulerConfig(JobLauncher jobLauncher, JobExplorer jobExplorer,
                           @Qualifier("saveFitnessDataEveryDayJob") Job saveFitnessDataEveryDayJob,
                           @Qualifier("updateCriteriaEveryYearJob") Job updateCriteriaEveryYearJob) {
        this.jobLauncher = jobLauncher;
        this.jobExplorer = jobExplorer;
        this.saveFitnessDataEveryDayJob = saveFitnessDataEveryDayJob;
        this.updateCriteriaEveryYearJob = updateCriteriaEveryYearJob;
    }

//    @Scheduled(cron = "0 0 4 * * *")
////    @Scheduled(cron = "0 * * * * *")
//    public void runTempSaveExercise() {
////        exerciseService.saveExerciseDataEveryDay();
//        List<Members> members = membersRepository.findAll();
//
//        for (Members member : members) {
//
//            try {
//                String accessToken = memberDataService.refreshAccessToken(member);
//
//                // 받아온 액세스 토큰으로 구글 피트니스 데이터 가져오기
//                LocalDateTime now = LocalDateTime.now();
//                LocalDateTime startOfYesterday = now.minusDays(1).toLocalDate().atStartOfDay();
//                LocalDateTime endOfYesterday = startOfYesterday.plusDays(1).minusNanos(1);
//
//                long startTimeMillis = startOfYesterday.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//                long endTimeMillis = endOfYesterday.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//                FitnessResponse fitnessResponse = exerciseService.fetchGoogleFitData(accessToken, startTimeMillis, endTimeMillis);
//                exerciseService.mapFitnessResponseToExercise(fitnessResponse, member);
//            } catch (Exception e) {
//                log.info(member.getNickname() + " 씨는 에러가 났다.", e);
//            }
//
//        }
//
//    }


    @Scheduled(cron = "0 30 * * * *")
//    @Scheduled(cron = "0 0 4 * * *")
    public void runBatchJob() {
        Set<JobExecution> executions = jobExplorer.findRunningJobExecutions(saveFitnessDataEveryDayJob.getName());
        if (!executions.isEmpty()) {
            log.info("이전 작업이 아직 실행 중입니다. 새 작업을 시작하지 않습니다.");
            return;
        }

        retryFailedJobs(saveFitnessDataEveryDayJob);

        // 정규 배치 작업
        try {
            // 현재 시간을 JobParameters에 추가, 중단됐던 job 실행할 때 활용
            JobParameters parameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(saveFitnessDataEveryDayJob, parameters);
        } catch (Exception e) {
            log.error("배치 작업 실행 중 에러가 발생했습니다.", e);
        }
    }

    private void retryFailedJobs(Job job) {
        List<JobInstance> jobInstances = jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE);
        for (JobInstance instance : jobInstances) {
            List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(instance);
            for (JobExecution execution : jobExecutions) {
                if (execution.getStatus() == BatchStatus.FAILED) {
                    try {
                        // 실패한 잡의 원래 JobParameters 가져오기
                        JobParameters originalParameters = execution.getJobParameters();
                        // 실패한 잡 재시도
                        jobLauncher.run(job, originalParameters);
                    } catch (JobExecutionAlreadyRunningException | JobRestartException |
                             JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                        System.out.println("재시도 중 에러가 발생했습니다: " + e.getMessage());
                    }
                }
            }
        }
    }

    @Scheduled(cron = "0 0 3 1 1 *")
    public void adjustAgeGroup() {
        Set<JobExecution> executions = jobExplorer.findRunningJobExecutions(updateCriteriaEveryYearJob.getName());
        if (!executions.isEmpty()) {
            log.info("이전 작업이 아직 실행 중입니다. 새 작업을 시작하지 않습니다.");
            return;
        }

        retryFailedJobs(updateCriteriaEveryYearJob);

        // 정규 배치 작업
        try {
            // 현재 시간을 JobParameters에 추가, 중단됐던 job 실행할 때 활용
            JobParameters parameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(updateCriteriaEveryYearJob, parameters);
        } catch (Exception e) {
            log.error("배치 작업 실행 중 에러가 발생했습니다.", e);
        }
    }
}

