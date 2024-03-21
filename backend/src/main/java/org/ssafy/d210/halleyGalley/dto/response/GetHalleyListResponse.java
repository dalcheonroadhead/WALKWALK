package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.halleyGalley.dto.HalleyDto;
import org.ssafy.d210.halleyGalley.dto.RecommendedExerciseDto;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetHalleyListResponse {

    private LocalDateTime timeStamp;
    private Long exerciseTime;
    private RecommendedExerciseDto criteriaContent;
    private List<HalleyDto> requestContent;

    @Builder
    private GetHalleyListResponse(LocalDateTime timeStamp, Long exerciseTime, RecommendedExerciseDto criteriaContent, List<HalleyDto> requestContent){
        this.timeStamp = timeStamp;
        this.exerciseTime = exerciseTime;
        this.criteriaContent = criteriaContent;
        this.requestContent = requestContent;
    }

    public static GetHalleyListResponse from(Members member, List<HalleyDto> requestContent){
        return builder()
                .timeStamp(member.getCreatedAt())
                .exerciseTime(100L)
                .criteriaContent(
                        RecommendedExerciseDto.builder()
                                .timeStamp(member.getCreatedAt())
                                .exerciseTime(member.getDailyCriteria())
                                .build()
                )
                .requestContent(requestContent)
                .build();
    }
}
