package org.ssafy.d210.members.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.ssafy.d210._common.entity.OnlyCreatedTime;

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




}
