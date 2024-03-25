package org.ssafy.d210.friends.dto.request;

import lombok.Getter;

@Getter
public class PutFriendRequest {
    private Long memberId;
    private Boolean isAccept;
}
