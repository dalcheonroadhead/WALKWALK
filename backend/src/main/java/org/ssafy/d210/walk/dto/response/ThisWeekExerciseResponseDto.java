package org.ssafy.d210.walk.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ThisWeekExerciseResponseDto {
    private LocalDate timeStamp;
    private Long steps;
}
