package org.ssafy.d210.wallets._blockchain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.ssafy.d210.wallets._blockchain.dto.response.PostIssueAccountResponse;
import org.ssafy.d210.wallets._blockchain.entity.Receipt;
import org.ssafy.d210.wallets._blockchain.entity.ServerBlockchainAccount;
import org.ssafy.d210.wallets._blockchain.repository.BlockchainRepository;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;
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

    private final WalkWalkContractService walkWalkContractService;

    public BlockchainService(RestTemplateBuilder restTemplateBuilder, BlockchainRepository blockchainRepository, WalkWalkContractService walkWalkContractService) {
        this.restTemplate = restTemplateBuilder.build();
        this.blockchainRepository = blockchainRepository;
        this.walkWalkContractService = walkWalkContractService;
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

    public BigInteger writeReceiptToBlockchain(Receipt receipt) throws Exception {
        TransactionReceipt transactionReceipt = walkWalkContractService.addReceipt(
                receipt.getWalletType(),
                receipt.isOperator(),
                receipt.getPrice(),
                receipt.getDescription(),
                receipt.getCreatedAt(),
                receipt.getMemberId()

        );

        return getReceiptIdFromTransactionReceipt(transactionReceipt);
    }

    public BigInteger getReceiptIdFromTransactionReceipt(TransactionReceipt transactionReceipt) throws Exception {
        // ReceiptAdded 이벤트의 해시를 생성. 이 값은 Solidity 이벤트 정의에서 추출할 수 있음
        String eventHash = EventEncoder.encode(ReceiptAddedEvent.RECEIPT_ADDED_EVENT);

        // 트랜잭션 영수증에서 모든 로그를 검사하고, 이벤트 해시와 일치하는 로그를 찾음
        for (Log log : transactionReceipt.getLogs()) {
            if (!log.getTopics().isEmpty() && log.getTopics().get(0).equals(eventHash)) {
                // 이벤트 로그에서 receiptId를 추출합니다. 이 값은 topics의 두 번째 요소에 있습니다.
                return Numeric.toBigInt(log.getTopics().get(1));
            }
        }

        // 일치하는 로그를 찾지 못한 경우
        throw new Exception("ReceiptAdded event log not found in the transaction receipt");
    }
}

class ReceiptAddedEvent {
    public static final Event RECEIPT_ADDED_EVENT = new Event("ReceiptAdded",
            Arrays.asList(
                    new TypeReference<Uint256>(true) {
                    }, // receiptId (indexed)
                    new TypeReference<Utf8String>() {
                    }, // walletType
                    new TypeReference<Bool>() {
                    }, // operator
                    new TypeReference<Uint256>() {
                    }, // price
                    new TypeReference<Utf8String>() {
                    }, // description
                    new TypeReference<Utf8String>() {
                    }, // createdAt
                    new TypeReference<Uint256>() {
                    } // memberId
            ));
}