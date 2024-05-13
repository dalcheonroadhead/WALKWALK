package org.ssafy.d210.walk.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.walk.dto.response.ReportResponseDto;
import org.ssafy.d210.walk.service.ExerciseDecidedService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walk/decided")
@Slf4j
public class ExerciseDecidedController {

    private final ExerciseDecidedService exerciseDecidedService;

    @Operation(summary = "운동 시작")
    @GetMapping("/start")
    public ApiResponseDto<?> setStartDecideExercise(@RequestParam(value = "start-time", required = true) LocalDateTime startTime, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseUtils.ok(exerciseDecidedService.saveStartTime(userDetails.getMember(), startTime), MsgType.SET_DECIDED_EXERCISE_START_SUCCESSFULLY);
    }

    @Operation(summary = "운동 끝! 작정한 운동 저장!")
    @GetMapping("/end")
    public ApiResponseDto<?> setEndDecideExercise(@RequestParam(value = "end-time", required = true) LocalDateTime endTime, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseUtils.ok(exerciseDecidedService.saveEndTime(userDetails.getMember(), endTime), MsgType.SET_DECIDED_EXERCISE_END_SUCCESSFULLY);
    }

    @Operation(summary = "지난 달 리포트")
    @GetMapping("/report")
    public ApiResponseDto<?> getLastMonthReport(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        ReportResponseDto report = exerciseDecidedService.makeExerciseReport(userDetails.getMember());
        return ResponseUtils.ok(report != null ? report : "지난 달 작정한 운동 데이터가 없습니다!", MsgType.GET_REPORT_SUCCESSFULLY);
    }

    @Operation(summary = "이 사람 운동 중인지")
    @GetMapping("/ing-check/{member-id}")
    public ApiResponseDto<?> getWhetherExerciseStart(@PathVariable(name = "member-id", required = true) Long memberId) {
        Boolean value = exerciseDecidedService.checkExerciseStarted(memberId);
        return ResponseUtils.ok(value != null ? value : "해당 회원이 존재하지 않습니다.", MsgType.GET_DECIDED_EXERCISE_STARTED_SUCCESSFULLY);
    }
}
