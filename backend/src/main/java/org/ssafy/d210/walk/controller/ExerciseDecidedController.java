package org.ssafy.d210.walk.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.walk.service.ExerciseDecidedService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walk/decided")
@Slf4j
public class ExerciseDecidedController {

    private final ExerciseDecidedService exerciseDecidedService;

    @Operation(summary = "운동 시작")
    @GetMapping("/start/{startTime}")
    public ApiResponseDto<?> setStartDecideExercise(@PathVariable(value = "startTime", required = true) LocalDateTime startTime, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseUtils.ok(exerciseDecidedService.)
    }
}
