package org.ssafy.d210.wallets.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@RequiredArgsConstructor
public class PutEggRequest {
    @NotNull(message = "변경할 Egg 값이 없습니다.")
    private Integer putEggValue;
}
