package org.ssafy.d210.wallets._blockchain.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostIssueAccountResponse {
    private String address;
    private String chainId;
    private String keyId;
    private String publicKey;
    private String krn;
    private String createdAt;
    private String updatedAt;
}
