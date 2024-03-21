package org.ssafy.d210.walk.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.walk.service.ExerciseService;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walk")
@Slf4j
public class ExerciseController {

    private final ExerciseService exerciseService;

    // 이번 주 운동 데이터(걸음 수) 조회
    @GetMapping("/{date}")
    public ApiResponseDto<?> getWeeklyExerciseRecords(@PathVariable(value = "date", required = true) LocalDate date, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseUtils.ok(exerciseService.findWeeklyExerciseRecords(date, userDetails.getMember().getId()), MsgType.GET_WEEKLY_EXERCISE_DATA_SUCCESSFULLY);
    }
}
