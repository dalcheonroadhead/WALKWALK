package org.ssafy.d210.wallets._payment.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PaymentExchangeRequest {

    @NotNull(message = "환전할 머니량을 입력해주세요.")
    private Integer exchangeMoneyValue;
}
