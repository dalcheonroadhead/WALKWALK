package org.ssafy.d210.wallets._blockchain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class GetReceiptResponse {
    private Long id;
    private String walletType;
    private Boolean operator;
    private Integer price;
    private String description;
    private LocalDateTime createdAt;
    private String receiptId;

    @Builder
    private GetReceiptResponse(Long id, String walletType, Boolean operator, Integer price, String description, LocalDateTime createdAt, String receiptId) {
        this.id = id;
        this.walletType = walletType;
        this.operator = operator;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.receiptId = receiptId;
    }
}