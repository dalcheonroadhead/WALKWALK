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
    private Long dayOfExercise;
    private Double stepsAvg;
    private Double exerciseMinuteAvg;
    private Double calorieAvg;

}
