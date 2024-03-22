package org.ssafy.d210.walk.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StreakRankingResopnseDto {
    private Long rank;
    private Long memberId;
    private String nickname;
    private String profileUrl;
    private Long streak;

    public StreakRankingResopnseDto(Long rank, Long memberId, String nickname, String profileUrl, Long streak) {
        this.rank = rank;
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.streak = streak;
    }
}
