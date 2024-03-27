package org.ssafy.d210.wallets._payment.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentExchangeResponse {

    private Integer putMoneyValue;

    @Builder
    private PaymentExchangeResponse(Integer putMoneyValue) {
        this.putMoneyValue = putMoneyValue;
    }

    public static PaymentExchangeResponse of(Integer putMoneyValue) {
        return builder()
                .putMoneyValue(putMoneyValue)
                .build();
    }
}
