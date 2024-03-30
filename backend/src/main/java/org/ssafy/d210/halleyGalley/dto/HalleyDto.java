package org.ssafy.d210.halleyGalley.dto;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;

import java.time.LocalDateTime;

@Getter
public class HalleyDto {
    private String profileUrl;
    private LocalDateTime timeStamp;
    private Long memberId;
    private String nickname;
    private Long requestedTime;
    private Boolean isAccepted;

    @Builder
    private HalleyDto(String profileUrl, LocalDateTime timeStamp, Long memberId, String nickname, Long requestedTime, Boolean isAccepted){
        this.profileUrl = profileUrl;
        this.timeStamp = timeStamp;
        this.memberId = memberId;
        this.nickname = nickname;
        this.requestedTime = requestedTime;
        this.isAccepted = isAccepted;
    }

    public static HalleyDto from(HalleyGalley halleyGalley){
        return builder()
                .profileUrl(halleyGalley.getHalleyId().getProfileUrl())
                .timeStamp(halleyGalley.getCreatedAt())
                .memberId(halleyGalley.getHalleyId().getId())
                .nickname(halleyGalley.getHalleyId().getNickname())
                .requestedTime(halleyGalley.getMissionId() == null ? null : halleyGalley.getMissionId().getExerciseMinute())
                .isAccepted(halleyGalley.getIsAccepted())
                .build();
    }
}
