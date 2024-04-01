package org.ssafy.d210.wallets.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import org.ssafy.d210.wallets.entity.WalletHistory;

import java.time.LocalDateTime;

@ToString
@Getter
public class GetWalletHistoryResponse {
    private Long walletHistoryId;
    private String walletType;
    private Boolean operator;
    private Integer price;
    private LocalDateTime createdAt;

    @Builder
    private GetWalletHistoryResponse(Long walletHistoryId, String walletType, Boolean operator, Integer price, LocalDateTime createdAt) {
        this.walletHistoryId = walletHistoryId;
        this.walletType = walletType;
        this.operator = operator;
        this.price = price;
        this.createdAt = createdAt;
    }

    public static GetWalletHistoryResponse from(WalletHistory walletHistory) {
        return builder()
                .walletHistoryId(walletHistory.getId())
                .walletType(walletHistory.getWalletType().name())
                .operator(walletHistory.getOperator())
                .price(walletHistory.getPrice())
                .createdAt(walletHistory.getCreatedAt())
                .build();
    }
}
