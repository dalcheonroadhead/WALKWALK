package org.ssafy.d210.walk.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class FriendRankingResponseDto {
    @Setter
    private Long rank;
    private Long memberId;
    private String nickname;
    private String profileUrl;
    private Long value;

    @Builder
    public FriendRankingResponseDto(Long memberId, String nickname, String profileUrl, Number value, Number rank) {
        this.rank = (long) rank;
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.value = (long) value;
    }
}
