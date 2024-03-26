package org.ssafy.d210.wallets.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PutHalleyGalleyMoneyRequest {

    @NotBlank(message = "미션을 설정한 할리의 memberId를 입력해주세요.")
    private Long halleyId;

    @NotNull(message = "지급할 money 량을 입력해주세요.")
    private Integer putMoneyValue;
}
