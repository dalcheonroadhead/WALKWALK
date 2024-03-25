package org.ssafy.d210.walk.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.walk.dto.response.SliceResponseDto;
import org.ssafy.d210.walk.dto.response.StreakRankingResopnseDto;
import org.ssafy.d210.walk.dto.response.ThisWeekExerciseResponseDto;
import org.ssafy.d210.walk.entity.Exercise;
import org.ssafy.d210.walk.repository.ExerciseRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final MembersRepository membersRepository;

    // db에 저장된 마지막 날짜
    public LocalDate findLastSavedDate() { return exerciseRepository.findLastDate(); }

    // 오늘 날짜만 받으면 이번주 월요일부터 어제까지 데이터를 조회하고 나머지 날은 디폴트로
    @Transactional
    public Map<String, Object> findWeeklyExerciseRecords(LocalDate today, Long memberId) {
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        Map<String, Object> data = new HashMap<>();
        System.out.println(exerciseRepository.findExercisesFromStartOfWeekToYesterday(startOfWeek, today, memberId));
        List<Exercise> exercises = exerciseRepository.findExercisesFromStartOfWeekToYesterday(startOfWeek, today, memberId);
        List<ThisWeekExerciseResponseDto> weeklyExercises = new ArrayList<>();
        int summ = 0;
        int supp = 0;

        for (LocalDate date = startOfWeek; date.isBefore(today); date = date.plusDays(1)) {
            LocalDate finalDate = date;
            ThisWeekExerciseResponseDto exercise = exercises.stream()
                    .filter(e -> e.getExerciseDay().equals(finalDate))
                    .findFirst()
                    .map(e -> ThisWeekExerciseResponseDto.builder().timeStamp(finalDate).steps(e.getSteps()).build())
                    .orElseGet(() -> ThisWeekExerciseResponseDto.builder().timeStamp(finalDate).steps(0L).build());

            weeklyExercises.add(exercise);
            summ += exercise.getSteps();
            supp++;
        }

        Integer avgValue = 0;
        if (supp != 0) avgValue = (int) Math.round((double) summ / supp);

        // 여기서 나머지 요일에 대해 기본값을 설정
        for (LocalDate date = today; date.isBefore(startOfWeek.plusWeeks(1)); date = date.plusDays(1)) {
            weeklyExercises.add(ThisWeekExerciseResponseDto.builder().timeStamp(date).steps(0L).build());
        }

        data.put("content", weeklyExercises);
        data.put("avg", avgValue);

        return data;
    }

    public SliceResponseDto getRankingWithFriends(Members member, Pageable pageable) {
//        Members member = membersRepository.findById(memberId).orElseThrow();
        Long myId = member.getId();
        Slice<StreakRankingResopnseDto> exercises = exerciseRepository.findRankingByPage(myId, pageable, LocalDate.now().minusDays(1));

        // 시작 순위 계산
        int startRank = pageable.getPageNumber() * pageable.getPageSize() + 1;

        for (StreakRankingResopnseDto exercise : exercises) {
            exercise.setRank((long) startRank++);
        }

        return new SliceResponseDto(exercises);
    }
}
