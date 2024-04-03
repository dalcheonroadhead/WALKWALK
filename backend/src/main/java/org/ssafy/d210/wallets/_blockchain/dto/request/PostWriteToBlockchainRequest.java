package org.ssafy.d210.wallets._blockchain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.wallets.entity.WalletHistory;
import org.ssafy.d210.wallets.entity.WalletType;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class PostWriteToBlockchainRequest {
    private Long walletHistoryId;
    private WalletType walletType;
    private Boolean operator;
    private Integer price;
    private String description;
    private Members member;
    private LocalDateTime createdAt;

    @Builder
    private PostWriteToBlockchainRequest(Long walletHistoryId, WalletType walletType, Boolean operator, Integer price, Members member, String description, LocalDateTime createdAt) {
        this.walletHistoryId = walletHistoryId;
        this.walletType = walletType;
        this.operator = operator;
        this.price = price;
        this.member = member;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static PostWriteToBlockchainRequest of(Long walletHistoryId, WalletHistory walletHistory, String description, LocalDateTime createdAt) {
        return builder()
                .walletHistoryId(walletHistoryId)
                .walletType(walletHistory.getWalletType())
                .operator(walletHistory.getOperator())
                .price(walletHistory.getPrice())
                .member(walletHistory.getMember())
                .description(description)
                .createdAt(createdAt)
                .build();
    }
}
