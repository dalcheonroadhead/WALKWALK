package org.ssafy.d210._common.request;

import lombok.*;
import org.ssafy.d210.members.entity.MsgType;
import org.ssafy.d210.members.entity.VoiceMessage;
import org.ssafy.d210.members.repository.MembersRepository;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MessageInfo {



    // 1. [TTS]인가, 바로 음성인가
    private String messageType;

    // 2. [TTS]일 경우 내용물, 음성 메세지면 "음성 메세지로 채우기"
    private String textContent;

    // 3. 음성 URL
    private String voiceURL;

    // 3. 보낸 이
    private Long senderId;

    // 4. 받는 이
    private Long receiverId;

    // 5. 보낸 사람 프로필 사진
    private String senderProfileUrl;

    // 6. 받는 사람 닉네임
    private String receiverNickname;

    // 7. 메세지 생성 일자
    private LocalDateTime createdAt;

    // 8. 알림 여부
    private boolean isOpened;

    @Builder
    private MessageInfo (String messageType, String textContent, String voiceURL, Long senderId, Long receiverId,
                         String senderProfileUrl, String receiverNickname, LocalDateTime createdAt, boolean isOpened) {
        this.messageType = messageType;
        this.textContent = textContent;
        this.voiceURL = voiceURL;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderProfileUrl = senderProfileUrl;
        this.receiverNickname = receiverNickname;
        this.createdAt = createdAt;
        this.isOpened = isOpened;

    }

    public static MessageInfo toDto (Long senderId, String senderProfileUrl, String textContent, Long receiverId,
                                     String receiverNickname, String voiceURL,
                                     String messageType, LocalDateTime createdAt, boolean isOpened) {

        return  builder()
                .senderId(senderId)
                .senderProfileUrl(senderProfileUrl)
                .receiverId(receiverId)
                .receiverNickname(receiverNickname)
                .voiceURL(voiceURL)
                .isOpened(isOpened)
                .textContent(textContent)
                .messageType(messageType)
                .createdAt(createdAt)
                .build();


    }


}
