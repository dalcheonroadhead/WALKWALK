package org.ssafy.d210.wallets._blockchain.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Getter
public class Receipt {
    private String walletType;
    private boolean operator;
    private BigInteger price;
    private String description;
    private String createdAt;
    private BigInteger memberId;

    @Builder
    private Receipt(String walletType, boolean operator, BigInteger price, String description, String createdAt, BigInteger memberId) {
        this.walletType = walletType;
        this.operator = operator;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.memberId = memberId;
    }

    public static Receipt of(String walletType, boolean operator, BigInteger price, String description, String createdAt, BigInteger memberId) {
        return builder()
                .walletType(walletType)
                .operator(operator)
                .price(price)
                .description(description)
                .createdAt(createdAt)
                .memberId(memberId)
                .build();
    }
}