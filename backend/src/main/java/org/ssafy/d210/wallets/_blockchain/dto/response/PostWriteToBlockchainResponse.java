package org.ssafy.d210.wallets._blockchain.dto.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostWriteToBlockchainResponse {
    private String filename;
    private String contentType;
    private String uri;
}
