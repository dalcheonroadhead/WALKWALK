package org.ssafy.d210.friends.dto;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.friends.entity.FriendList;

@Getter
public class FriendReceivedDto {
    private String profileUrl;
    private String nickname;
    private Long memberId;
    private String comment;

    @Builder
    private FriendReceivedDto(String profileUrl, String nickname, Long memberId, String comment){
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.memberId = memberId;
        this.comment = comment;
    }

    public static FriendReceivedDto from(FriendList friendList){
        return builder()
                .profileUrl(friendList.getSenderId().getProfileUrl())
                .nickname(friendList.getSenderId().getNickname())
                .memberId(friendList.getSenderId().getId())
                .comment(friendList.getSenderId().getComment())
                .build();
    }
}
