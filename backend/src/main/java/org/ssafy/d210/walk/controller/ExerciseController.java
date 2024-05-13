package org.ssafy.d210.walk.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.walk.dto.request.StepsRankingPeriodEnum;
import org.ssafy.d210.walk.dto.response.SliceResponseDto;
import org.ssafy.d210.walk.entity.Exercise;
import org.ssafy.d210.walk.service.ExerciseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walk")
@Slf4j
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(summary = "마지막으로 db에 저장된 날짜")
    @GetMapping("/last-date")
    public ApiResponseDto<?> getLastDate() {
        return ResponseUtils.ok(exerciseService.findLastSavedDate(), MsgType.GET_LAST_SAVED_DATE_SUCCESSFULLY);
    }

    @Operation(summary = "이번 주 운동 데이터(걸음 수) 조회")
    @GetMapping("/{date}")
    public ApiResponseDto<?> getWeeklyExerciseRecords(@PathVariable(value = "date", required = true) LocalDate date, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseUtils.ok(exerciseService.findWeeklyExerciseRecords(date, userDetails.getMember().getId()), MsgType.GET_WEEKLY_EXERCISE_DATA_SUCCESSFULLY);
    }

    @Operation(summary = "랭킹 조회, 내 위아래 4명")
    @GetMapping("/ranking/streak")
    public ApiResponseDto<SliceResponseDto> getMemberRankingWithFriends(@PageableDefault(size = 10, sort = "streak", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseUtils.ok(exerciseService.getStreakRankingWithFriends(userDetails.getMember(), pageable), MsgType.GET_STREAK_RANKING_SUCCESSFULLY);
    }

    @Operation(summary = "걸음 수 랭킹")
    @GetMapping("/ranking/steps/{type}")
    public ApiResponseDto<SliceResponseDto> getStepsRankingWithFriends(@PageableDefault(size = 10, sort = "steps", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable("type") String typeStr, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        StepsRankingPeriodEnum type = StepsRankingPeriodEnum.valueOf(typeStr.toUpperCase());
        return ResponseUtils.ok(exerciseService.getStepsRankingWithFriends(userDetails.getMember(), type, pageable), MsgType.GET_STEPS_RANKING_SUCCESSFULLY);
    }

    @Operation(summary = "한 달 운동")
    @GetMapping("/calendar/{member-id}")
    public ApiResponseDto<?> getMonthlyExerciseDataForCalendar(@PathVariable(name = "member-id", required = true) Long memberId) {
        List<Exercise> exercises = exerciseService.findMonthlyExerciseData(memberId);
        return ResponseUtils.ok(exercises != null ? exercises : "멤버가 존재하지 않습니다.", MsgType.GET_MONTHLY_EXERCISE_DATA_SUCCESSFULLY);
    }

    @Operation(summary = "그 날의 운동 데이터 조회")
    @GetMapping("/calendar/{member-id}/{date}")
    public ApiResponseDto<?> getCalendarDailyRecord(@PathVariable(value = "member-id", required = true) Long memberId, @PathVariable(value = "date", required = true) LocalDate date) {
        Exercise exercise = exerciseService.findDailyFromCalendar(memberId, date);
        return ResponseUtils.ok(exercise != null ? exercise : "멤버가 존재하지 않거나 해당일 운동 기록이 없습니다.", MsgType.GET_DAILY_FROM_CALENDAR_SUCCESSFULLY);
    }

    @Operation(summary = "어제와 나 용 해당일 운동 데이터 조회")
    @GetMapping("/vs-me/{date}")
    public ApiResponseDto<?> getCalendarDailyRecord(@PathVariable(value = "date", required = true) LocalDate date, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Exercise exercise = exerciseService.getExerciseDataFromDate(userDetails.getMember(), date);
        return ResponseUtils.ok(exercise != null ? exercise : "해당일 운동 기록이 없습니다.", MsgType.GET_DAILY_FROM_CALENDAR_SUCCESSFULLY);
    }
}
