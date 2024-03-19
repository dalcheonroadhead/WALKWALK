package org.ssafy.d210.friends.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ssafy.d210.members.entity.Members;

@Entity
@Getter
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
}
