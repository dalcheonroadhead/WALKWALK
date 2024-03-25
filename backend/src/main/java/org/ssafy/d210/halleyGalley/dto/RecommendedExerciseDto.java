package org.ssafy.d210.halleyGalley.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecommendedExerciseDto {
    private LocalDateTime timeStamp;
    private Long exerciseTime;

    @Builder
    private RecommendedExerciseDto(LocalDateTime timeStamp, Long exerciseTime){
        this.timeStamp = timeStamp;
        this.exerciseTime = exerciseTime;
    }

}
