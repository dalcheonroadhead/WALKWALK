package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.time.LocalDateTime;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;

@ToString
@Getter
public class GetGalleyListResponse {
    private LocalDateTime timeStamp;
    private Long memberId;
    private String nickname;
    private Long requestedTime;

    @Builder
    private GetGalleyListResponse(LocalDateTime timeStamp, Long memberId, String nickname, Long requestedTime){
        this.timeStamp = timeStamp;
        this.memberId = memberId;
        this.nickname = nickname;
        this.requestedTime = requestedTime;
    }

    public static GetGalleyListResponse from(HalleyGalley halleyGalley){
        return builder()
                .timeStamp(halleyGalley.getCreatedAt())
                .memberId(halleyGalley.getGalleyId().getId())
                .nickname(halleyGalley.getGalleyId().getNickname())
                .requestedTime(halleyGalley.getMissionId() == null ? null : halleyGalley.getMissionId().getExerciseMinute())
                .build();
    }

}
