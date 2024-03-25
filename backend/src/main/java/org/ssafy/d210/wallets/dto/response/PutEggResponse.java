package org.ssafy.d210.wallets.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PutEggResponse {

    private Integer egg;

    @Builder
    private PutEggResponse(Integer egg) {
        this.egg = egg;
    }

    public static PutEggResponse of(Integer egg) {
        return builder()
                .egg(egg)
                .build();
    }
}
