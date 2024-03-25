package org.ssafy.d210.walk.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class StreakRankingResopnseDto {
    @Setter
    private Long rank;
    private Long memberId;
    private String nickname;
    private String profileUrl;
    private Long streak;

    @Builder
    public StreakRankingResopnseDto(Long memberId, String nickname, String profileUrl, Long streak) {
        this.rank = 0L;
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileUrl = profileUrl;
        this.streak = streak;
    }
}
