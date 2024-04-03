package org.ssafy.d210.wallets.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PutMoneyResponse {
    private Integer money;

    @Builder
    private PutMoneyResponse(Integer money) {
        this.money = money;
    }

    public static PutMoneyResponse of(Integer money) {
        return builder()
                .money(money)
                .build();
    }
}
