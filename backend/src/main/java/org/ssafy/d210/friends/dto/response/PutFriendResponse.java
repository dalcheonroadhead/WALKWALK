package org.ssafy.d210.friends.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PutFriendResponse {
    private Boolean isFriend;

    @Builder
    private PutFriendResponse(Boolean isFriend){
        this.isFriend = isFriend;
    }

    public static PutFriendResponse of(Boolean isFriend){
        return builder().isFriend(isFriend).build();
    }
}
