package org.ssafy.d210.walk.dto.request;

import lombok.Getter;

@Getter
public class CustomExerciseCriteriaRequestDto {
    private Long steps;
    private Long exerciseMinute;
    private Long heartRate;
    private Long exerciseDistance;
}
