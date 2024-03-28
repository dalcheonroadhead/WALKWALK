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
    private String profileUrl;
    private Long memberId;
    private String nickname;
    private Long requestedTime;
    private Boolean isAccepted;

    @Builder
    private GetGalleyListResponse(LocalDateTime timeStamp, String profileUrl, Long memberId, String nickname, Long requestedTime, Boolean isAccepted){
        this.timeStamp = timeStamp;
        this.profileUrl = profileUrl;
        this.memberId = memberId;
        this.nickname = nickname;
        this.requestedTime = requestedTime;
        this.isAccepted = isAccepted;
    }

    public static GetGalleyListResponse from(HalleyGalley halleyGalley){
        return builder()
                .timeStamp(halleyGalley.getCreatedAt())
                .profileUrl(halleyGalley.getGalleyId().getProfileUrl())
                .memberId(halleyGalley.getGalleyId().getId())
                .nickname(halleyGalley.getGalleyId().getNickname())
                .requestedTime(halleyGalley.getMissionId() == null ? null : halleyGalley.getMissionId().getExerciseMinute())
                .isAccepted(halleyGalley.getIsAccepted())
                .build();
    }

}
