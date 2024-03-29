package org.ssafy.d210.notifications.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.d210._common.entity.OnlyCreatedTime;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
@NoArgsConstructor
public class Notification extends OnlyCreatedTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Members senderId;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Members receiverId;

    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private NotiType notiType;

    @Column(name = "noti_content", nullable = false)
    private String notiContent;

    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked;

    @Builder
    private Notification(Members senderId, Members receiverId, NotiType notiType, String notiContent, Boolean isChecked){

        this.senderId = senderId;
        this.receiverId = receiverId;
        this.notiType = notiType;
        this.notiContent = notiContent;
        this.isChecked = isChecked;
    }

    public static Notification of(Members senderId, Members receiverId, NotiType notiType, String notiContent, Boolean isChecked){
        return builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .notiType(notiType)
                .notiContent(notiContent)
                .isChecked(isChecked)
                .build();
    }

    public void updateIsChecked(Boolean isChecked){this.isChecked = isChecked;}
}
