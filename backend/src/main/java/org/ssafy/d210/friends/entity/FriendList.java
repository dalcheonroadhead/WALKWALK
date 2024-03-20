package org.ssafy.d210.friends.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "friend_list_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private Members senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Members receiverId;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted = false;

    @Builder
    private FriendList(Members senderId, Members receiverId, Boolean isAccepted){

        this.senderId = senderId;
        this.receiverId = receiverId;
        this.isAccepted = isAccepted;
    }

    public static FriendList of(Members senderId, Members receiverId, Boolean isAccepted){

        return builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .isAccepted(isAccepted)
                .build();
    }

}
