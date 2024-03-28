package org.ssafy.d210.wallets.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PutHalleyGalleyMoneyResponse {
    private Integer halleyMoney;
    private Integer galleyMoney;

    @Builder
    private PutHalleyGalleyMoneyResponse(Integer halleyMoney, Integer galleyMoney) {
        this.halleyMoney = halleyMoney;
        this.galleyMoney = galleyMoney;
    }

    public static PutHalleyGalleyMoneyResponse of(Integer halleyMoney, Integer galleyMoney) {
        return builder()
                .halleyMoney(halleyMoney)
                .galleyMoney(galleyMoney)
                .build();
    }
}
