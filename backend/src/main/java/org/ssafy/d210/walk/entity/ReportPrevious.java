package org.ssafy.d210.walk.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportPrevious {
    private Double heartRateAvg;
    private Double exerciseDistanceAvg;
    private Long stepsAvg;
    private Long exerciseMinuteAvg;
    private Double calorieAvg;

}
