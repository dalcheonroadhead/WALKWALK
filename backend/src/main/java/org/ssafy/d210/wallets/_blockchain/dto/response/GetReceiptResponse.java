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
    private String uri;


    @Builder
    private GetReceiptResponse(Long id, String walletType, Boolean operator, Integer price, String description, LocalDateTime createdAt, String uri) {
        this.id = id;
        this.walletType = walletType;
        this.operator = operator;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.uri = uri;
    }

    public static GetReceiptResponse from(GetReadFromBlockchainResponse getReadFromBlockchainResponse, String uri) {
        return builder()
                .id(getReadFromBlockchainResponse.getWalletHistoryId())
                .walletType(getReadFromBlockchainResponse.getWalletType())
                .operator(getReadFromBlockchainResponse.getOperator())
                .price(getReadFromBlockchainResponse.getPrice())
                .description(getReadFromBlockchainResponse.getDescription())
                .createdAt(getReadFromBlockchainResponse.getCreatedAt())
//                .createdAt(LocalDateTime.parse(getReadFromBlockchainResponse.getCreatedAt(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .uri(uri)
                .build();
    }
}
