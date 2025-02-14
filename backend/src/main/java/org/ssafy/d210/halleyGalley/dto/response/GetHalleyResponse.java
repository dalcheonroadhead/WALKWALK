package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.halleyGalley.entity.HalleyGalley;
import org.ssafy.d210.halleyGalley.entity.Mission;

@Getter
public class GetHalleyResponse {
    private String profileUrl;
    private String nickname;
    private Long requestedTime;
    private Integer reward;
    private Integer dayoff;
    private Integer period;

    @Builder
    private GetHalleyResponse(String profileUrl, String nickname, Long requestedTime, Integer reward, Integer dayoff, Integer period){
        this.profileUrl = profileUrl;
        this.nickname = nickname;
        this.requestedTime = requestedTime;
        this.reward = reward;
        this.dayoff = dayoff;
        this.period = period;
    }

    public static GetHalleyResponse from(HalleyGalley halley, Mission mission){
        return builder()
                .profileUrl(halley.getHalleyId().getProfileUrl())
                .nickname(halley.getHalleyId().getNickname())
                .requestedTime(mission == null ? -1 : mission.getExerciseMinute())
                .reward(halley.getReward())
                .dayoff(halley.getDayoff())
                .period(mission == null ? -1 : mission.getPeriod())
                .build();
    }
}
