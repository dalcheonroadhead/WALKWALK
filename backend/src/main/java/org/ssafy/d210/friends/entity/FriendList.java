package org.ssafy.d210.friends.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "friend_list_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Members senderId;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Members receiverId;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted;

    @Column(name = "is_friend", nullable = false)
    private Boolean isFriend;


    @Builder
    private FriendList(Members senderId, Members receiverId, Boolean isAccepted, Boolean isFriend){

        this.senderId = senderId;
        this.receiverId = receiverId;
        this.isAccepted = isAccepted;
        this.isFriend = isFriend;
    }

    public static FriendList of(Members senderId, Members receiverId, Boolean isAccepted, Boolean isFriend){

        return builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .isAccepted(isAccepted)
                .isFriend(isFriend)
                .build();
    }

    public void updateIsAccepted(Boolean isAccepted){
        this.isAccepted = isAccepted;
    }

    public void updateIsFriend(Boolean isFriend) { this.isFriend = isFriend; }
}
