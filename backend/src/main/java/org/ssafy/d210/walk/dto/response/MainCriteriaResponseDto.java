package org.ssafy.d210.walk.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainCriteriaResponseDto {
    private Long steps;
    private Long exerciseMinute;
    private Double exerciseDistance;
}
