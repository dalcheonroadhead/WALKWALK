package org.ssafy.d210.wallets._blockchain.dto.response;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class GetReadFromBlockchainResponse {
    private Long walletHistoryId;
    private String walletType;
    private Boolean operator;
    private Integer price;
    private Long memberId;
    private String description;
    private LocalDateTime createdAt;
}
