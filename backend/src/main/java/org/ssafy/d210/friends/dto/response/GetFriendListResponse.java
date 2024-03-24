package org.ssafy.d210.friends.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.friends.entity.FriendList;

@Getter
public class GetFriendListResponse {
    private String profileUrl;
    private String nickname;
    private Long memberId;

    @Builder
    private GetFriendListResponse(String profileUrl, String nickname, Long memberId){
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.memberId = memberId;
    }

    public static GetFriendListResponse from(FriendList friendList){
        return builder()
                .profileUrl(friendList.getReceiverId().getProfileUrl())
                .nickname(friendList.getReceiverId().getNickname())
                .memberId(friendList.getReceiverId().getId())
                .build();
    }
}
