package org.ssafy.d210.wallets.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PutEggMoneyResponse {
    private Integer egg;
    private Integer money;

    @Builder
    private PutEggMoneyResponse(Integer egg, Integer money) {
        this.egg = egg;
        this.money = money;
    }

    public static PutEggMoneyResponse of(Integer egg, Integer money) {
        return builder()
                .egg(egg)
                .money(money)
                .build();
    }
}
