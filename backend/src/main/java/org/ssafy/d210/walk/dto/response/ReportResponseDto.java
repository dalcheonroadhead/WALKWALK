package org.ssafy.d210.walk.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.members.entity.Members;

import java.util.List;

@Getter
@Builder
public class ReportResponseDto {
    private Members member;
    private List<String> mainExerciseTime;
    private Double heartRateAvg;
    private Double exerciseDistanceAvg;
    private Integer dayOfExercise;
    private Long stepsAvg;
    private Long exerciseMinuteAvg;
    private Double calorieAvg;
}
