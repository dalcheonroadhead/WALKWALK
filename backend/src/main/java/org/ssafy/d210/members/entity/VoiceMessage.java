package org.ssafy.d210.members.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210._common.entity.OnlyCreatedTime;
import org.ssafy.d210._common.request.MessageInfo;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "voice_message")
@Getter
public class VoiceMessage extends OnlyCreatedTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voice_message_id")
    private Long voiceMessageId;

    // 보내는 이는 여러 음성 파일의 주인 일 수 있지만,
    // 음성 파일은 보낸 이가 여러 명일 수 없다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender", nullable = false)
    private Members sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver", nullable = false)
    private Members receiver;

    @Column(name ="voice_addr", nullable = false)
    private String voiceAddr;

    @Column(name = "is_opened")
    @ColumnDefault("false")
    private boolean isOpened;

    @Column(name = "text")
//    @ColumnDefault("음성 메세지")
    private String text;

    @Column(name="msg_type")
    @Enumerated(EnumType.STRING)
    private MsgType msgType;


    @Builder
    private VoiceMessage (Members sender, Members receiver, String voiceAddr, boolean isOpened, String text, MsgType msgType ) {
        this.sender = sender;
        this.receiver = receiver;
        this.voiceAddr = voiceAddr;
        this.isOpened = isOpened;
        this.text = text;
        this.msgType = msgType;
    }


    public static VoiceMessage toEntity(Members sender, Members receiver, String voiceAddr, boolean isOpened, String text, MsgType msgType){

        return builder()
                .sender(sender)
                .receiver(receiver)
                .voiceAddr(voiceAddr)
                .isOpened(isOpened)
                .text(text)
                .msgType(msgType)
                .build();
    }



}
