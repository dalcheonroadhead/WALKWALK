package org.ssafy.d210.members.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class VoiceMessageInfo {
    String voiceUrl;

    LocalDateTime createdAt;

    boolean isOpened;


    @Builder
    public VoiceMessageInfo(String voiceUrl, LocalDateTime createdAt, boolean isOpened) {
        this.voiceUrl = voiceUrl;
        this.createdAt = createdAt;
        this.isOpened = isOpened;
    }

    public static VoiceMessageInfo of(String voiceUrl, LocalDateTime createdAt, boolean isOpened){
        return builder()
                .voiceUrl(voiceUrl)
                .createdAt(createdAt)
                .isOpened(isOpened)
                .build();
    }
}
