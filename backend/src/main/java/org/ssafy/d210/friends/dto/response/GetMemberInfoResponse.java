package org.ssafy.d210.friends.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.ssafy.d210.friends.entity.FriendList;
import org.ssafy.d210.members.entity.Members;

@ToString
@Getter
public class GetMemberInfoResponse {
    private String nickname;
    private String profileUrl;
    private String streakBackground;
    private String badge;
    private Boolean isFriend;

    @Builder
    private GetMemberInfoResponse(String nickname, String profileUrl, String streakBackground, String badge, Boolean isFriend){
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.streakBackground = streakBackground;
        this.badge = badge;
        this.isFriend = isFriend;
    }

    public static GetMemberInfoResponse from(Members member, Boolean isFriend){
        return builder()
                .nickname(member.getNickname())
                .profileUrl(member.getProfileUrl())
                .streakBackground(member.getStreakColor())
                .badge(member.getMainBadge())
                .isFriend(isFriend)
                .build();
    }

}
