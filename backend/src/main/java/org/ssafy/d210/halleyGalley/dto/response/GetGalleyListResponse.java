package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.entity.Mission;

@ToString
@Getter
public class GetGalleyListResponse {
    private LocalDateTime timeStamp;
    private Long memberId;
    private String nickname;
    private Integer exerciseTime;
    private Integer requestedTime;

    @Builder
    private GetGalleyListResponse(LocalDateTime timeStamp, Long memberId, String nickname, Integer exerciseTime, Integer requestedTime){
        this.timeStamp = timeStamp;
        this.memberId = memberId;
        this.nickname = nickname;
        this.exerciseTime = exerciseTime;
        this.requestedTime = requestedTime;
    }

    public static GetGalleyListResponse of(HalleyGalley halleyGalley, Mission mission){
        return builder()
                .timeStamp(halleyGalley.getCreatedAt())
                .memberId(halleyGalley.getGalleyId().getId())
                .nickname(halleyGalley.getGalleyId().getNickname())
                .exerciseTime(100)
                .requestedTime(mission.getExerciseMinute())
                .build();
    }

}
