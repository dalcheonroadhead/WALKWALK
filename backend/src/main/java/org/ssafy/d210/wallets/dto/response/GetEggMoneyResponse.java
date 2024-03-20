package org.ssafy.d210.wallets.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.ssafy.d210.wallets.entity.MemberAccount;

@ToString
@Getter
public class GetEggMoneyResponse {
    private Integer egg;
    private Integer money;

    @Builder
    private GetEggMoneyResponse(Integer egg, Integer money) {
        this.egg = egg;
        this.money = money;
    }

    public static GetEggMoneyResponse of(MemberAccount memberAccount) {
        return builder()
                .egg(memberAccount.getEgg())
                .money(memberAccount.getMoney())
                .build();
    }
}
