package org.ssafy.d210.walk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210.items.entity.ItemsType;
import org.ssafy.d210.items.entity.MemberItemHistory;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.service.MemberDataService;
import org.ssafy.d210.walk.dto.response.FitnessResponse;
import org.ssafy.d210.walk.dto.response.MainCriteriaResponseDto;
import org.ssafy.d210.walk.entity.Exercise;
import org.ssafy.d210.walk.entity.ExerciseDecided;
import org.ssafy.d210.walk.repository.ExerciseDecidedRepository;

import java.time.*;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseDecidedService {

    private final ExerciseDecidedRepository exerciseDecidedRepository;
    private final ExerciseService exerciseService;
    private final MemberDataService memberDataService;

    @Transactional
    public void saveStartTime(Members member, LocalDateTime startTime) {
        exerciseDecidedRepository.findExerciseDecidedByMemberAndAndExerciseEndIsNull(member);
    }

    @Transactional
    public void saveExerciseDecided(Members member, LocalDateTime startTime, LocalDateTime endTime) {

        String accessToken = memberDataService.refreshAccessToken(member);

        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();

        long startTimeMillis = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTimeMillis = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        FitnessResponse fitnessResponse;
        if (startDate.isEqual(endDate)) {
            fitnessResponse = exerciseService.fetchGoogleFitData(accessToken, startTimeMillis, endTimeMillis);
            mapFitnessResponseToExerciseDecided(fitnessResponse, member, startTime, endTime);
        } else {
            LocalDateTime firstEndTime = LocalDateTime.of(startDate, LocalTime.MAX);
            long firstEndTimeMillis = firstEndTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            LocalDateTime secondStartTime = LocalDateTime.of(endDate, LocalTime.MIN);
            long secondStartTimeMillis = secondStartTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

            fitnessResponse = exerciseService.fetchGoogleFitData(accessToken, startTimeMillis, firstEndTimeMillis);
            mapFitnessResponseToExerciseDecided(fitnessResponse, member, startTime, firstEndTime);

            fitnessResponse = exerciseService.fetchGoogleFitData(accessToken, secondStartTimeMillis, endTimeMillis);
            mapFitnessResponseToExerciseDecided(fitnessResponse, member, secondStartTime, endTime);
        }
    }

    public ExerciseDecided mapFitnessResponseToExerciseDecided(FitnessResponse fitnessResponse, Members member, LocalDateTime startTime, LocalDateTime endTime) {
        ExerciseDecided exercise = new ExerciseDecided();
        exercise.setMember(member);

        exercise.setExerciseStart(startTime);
        exercise.setExerciseEnd(endTime);

        LocalDate date = startTime.toLocalDate();
        exercise.setExerciseDay(date);

        for (FitnessResponse.Bucket bucket : fitnessResponse.getBucket()) {
            for (FitnessResponse.DataSet dataSet : bucket.getDataset()) {
                for (FitnessResponse.DataPoint dataPoint : dataSet.getPoint()) {
                    switch (dataPoint.getDataTypeName()) {
                        case "com.google.step_count.delta":
                            exercise.setSteps(dataPoint.getValue().get(0).getIntVal());
                            break;
                        case "com.google.distance.delta":
                            exercise.setExerciseDistance((double) dataPoint.getValue().get(0).getFpVal());
                            break;
                        case "com.google.calories.expended":
                            exercise.setCalorie((double) dataPoint.getValue().get(0).getFpVal());
                            break;
                        case "com.google.heart_rate.bpm":
                            exercise.setHeartRate((double) dataPoint.getValue().get(0).getFpVal());
                            break;
                        case "com.google.active_minutes":
                            exercise.setExerciseMinute(dataPoint.getValue().get(0).getIntVal());
                            break;
                    }
                }
            }
        }


        return exerciseDecidedRepository.save(exercise);
    }

}
