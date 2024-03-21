package org.ssafy.d210.walk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210.walk.dto.response.ThisWeekExerciseResponseDto;
import org.ssafy.d210.walk.entity.Exercise;
import org.ssafy.d210.walk.repository.ExerciseRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    // 오늘 날짜만 받으면 이번주 월요일부터 어제까지 데이터를 조회하고 나머지 날은 디폴트로
    @Transactional
    public List<ThisWeekExerciseResponseDto> findWeeklyExerciseRecords(LocalDate today, Long memberId) {
//        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        List<ThisWeekExerciseResponseDto> exercises = exerciseRepository.findExercisesFromStartOfWeekToYesterday(startOfWeek, today, memberId);
        List<ThisWeekExerciseResponseDto> weeklyExercises = new ArrayList<>();

        for (LocalDate date = startOfWeek; date.isBefore(today); date = date.plusDays(1)) {
            LocalDate finalDate = date;
            ThisWeekExerciseResponseDto exercise = exercises.stream()
                    .filter(e -> e.getTimeStamp().equals(finalDate))
                    .findFirst()
                    .orElseGet(() -> ThisWeekExerciseResponseDto.builder().timeStamp(finalDate).steps(0).build());

            weeklyExercises.add(exercise);
        }

        // 여기서 나머지 요일에 대해 기본값을 설정
        for (LocalDate date = today; date.isBefore(startOfWeek.plusWeeks(1)); date = date.plusDays(1)) {
            weeklyExercises.add(ThisWeekExerciseResponseDto.builder().timeStamp(date).steps(0).build());
        }

        return weeklyExercises;
    }
}
