package org.ssafy.d210.wallets._blockchain.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.ssafy.d210.wallets._blockchain.dto.request.PostWriteToBlockchainRequest;
import org.ssafy.d210.wallets._blockchain.dto.response.GetReadFromBlockchainResponse;
import org.ssafy.d210.wallets._blockchain.dto.response.PostIssueAccountResponse;
import org.ssafy.d210.wallets._blockchain.dto.response.PostWriteToBlockchainResponse;
import org.ssafy.d210.wallets._blockchain.entity.ServerBlockchainAccount;
import org.ssafy.d210.wallets._blockchain.repository.BlockchainRepository;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BlockchainService {

    @Value("${blockchain.auth}")
    private String authorization;

    @Value("${blockchain.chain-id}")
    private String chainId;

    private final String blockchainIssueUrl = "https://wallet-api.klaytnapi.com/v2/account";
    private final String blockchainWriteUrl = "https://metadata-api.klaytnapi.com/v1/metadata";

    private final RestTemplate restTemplate;

    private final BlockchainRepository blockchainRepository;

    public BlockchainService(RestTemplateBuilder restTemplateBuilder, BlockchainRepository blockchainRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.blockchainRepository = blockchainRepository;
    }

    // 첫 번째 : 초, 두 번째 : 분, 세 번째 : 시간
    // 네 번째 : 일 (매일), 다섯 번째 : 월 (매월), 여섯 번째 : 요일 (매주)
    // -> 매일 오전 01시에 실행
    @Scheduled(cron = "0 0 01 * * *", zone = "Asia/Seoul")
    public void connectOrIssueAccount() {
        // DB에 블록체인 서버 지갑있는지 확인
        Optional<ServerBlockchainAccount> optionalServerBlockchainAccount = blockchainRepository.findServerBlockchainAccountByChainId(chainId);

        log.info("ଘ(´ 3`)ଓ ~~~~ 스케줄러 체크 ~~~~ ଘ(´ 3`)ଓ");

        // 없으면 지갑 재발급
        if (optionalServerBlockchainAccount.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorization);
            headers.set("x-chain-id", chainId);

            HttpEntity<?> request = new HttpEntity<>(null, headers);
            ResponseEntity<PostIssueAccountResponse> response = restTemplate.postForEntity(blockchainIssueUrl, request, PostIssueAccountResponse.class);

            ServerBlockchainAccount serverBlockchainAccount = new ServerBlockchainAccount();
            serverBlockchainAccount.setEoa(response.getBody().getAddress());
            serverBlockchainAccount.setChainId(response.getBody().getChainId());
            serverBlockchainAccount.setKeyId(response.getBody().getKeyId());
            serverBlockchainAccount.setPublicKey(response.getBody().getPublicKey());
            serverBlockchainAccount.setKrn(response.getBody().getKrn());
            serverBlockchainAccount.setCreatedAt(response.getBody().getCreatedAt());
            serverBlockchainAccount.setUpdatedAt(response.getBody().getUpdatedAt());
            blockchainRepository.save(serverBlockchainAccount);
        }
    }

    public String writeToBlockchain(PostWriteToBlockchainRequest postWriteToBlockchainRequest) throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        headers.set("x-chain-id", chainId);

        JSONObject metadataContent = new JSONObject();
        metadataContent.put("walletHistoryId", postWriteToBlockchainRequest.getWalletHistoryId());
        metadataContent.put("walletType", postWriteToBlockchainRequest.getWalletType().name());
        metadataContent.put("operator", postWriteToBlockchainRequest.getOperator());
        metadataContent.put("price", postWriteToBlockchainRequest.getPrice());
        metadataContent.put("memberId", postWriteToBlockchainRequest.getMember().getId());
        metadataContent.put("description", postWriteToBlockchainRequest.getDescription());
        metadataContent.put("createdAt", postWriteToBlockchainRequest.getCreatedAt().toString());

        JSONObject requestBody = new JSONObject();
        requestBody.put("metadata", metadataContent);

        HttpEntity<?> request = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<PostWriteToBlockchainResponse> response = restTemplate.postForEntity(blockchainWriteUrl, request, PostWriteToBlockchainResponse.class);

        return Objects.requireNonNull(response.getBody()).getUri();
    }

    public GetReadFromBlockchainResponse readFromBlockchain(String uri) {
        return restTemplate.getForObject(uri, GetReadFromBlockchainResponse.class);
    }
}