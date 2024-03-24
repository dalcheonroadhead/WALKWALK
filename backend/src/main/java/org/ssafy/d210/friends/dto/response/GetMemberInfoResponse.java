package org.ssafy.d210.friends.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class GetMemberInfoResponse {
    private String nickname;
    private String profileUrl;
    private String badge;
    private Boolean isFriend;

    @Builder
    private GetMemberInfoResponse(String nickname, String profileUrl, String badge, Boolean isFriend){
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.badge = badge;
        this.isFriend = isFriend;
    }

}
