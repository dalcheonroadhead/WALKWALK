package org.ssafy.d210.wallets._blockchain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class WalkWalkContractService {

    private Web3j web3j;
    private String contractAddress;
    private Credentials credentials;

    public WalkWalkContractService(
            @Value("${BLOCKCHAIN_WEB3J_URL}") String web3jUrl,
            @Value("${BLOCKCHAIN_CONTRACT_ADDRESS}") String contractAddress,
            @Value("${BLOCKCHAIN_CREDENTIALS}") String privateKey) {
        this.web3j = Web3j.build(new HttpService(web3jUrl));
        this.contractAddress = contractAddress;
        this.credentials = Credentials.create(privateKey);
    }


    public TransactionReceipt addReceipt(String walletType, boolean operator, BigInteger price, String description, String createdAt, BigInteger memberId) throws Exception {
        // 함수 인코딩
        Function function = new Function(
                "addReceipt",
                Arrays.asList(new Utf8String(walletType), new Bool(operator), new Uint256(price), new Utf8String(description), new Utf8String(createdAt), new Uint256(memberId)),
                Collections.emptyList());
        String encodedFunction = FunctionEncoder.encode(function);

        // nonce 값 조회
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        // 트랜잭션 생성
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                DefaultGasProvider.GAS_PRICE,
                DefaultGasProvider.GAS_LIMIT,
                contractAddress,
                encodedFunction);

        // 트랜잭션 서명
        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        // 서명된 트랜잭션 전송
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();

        // 트랜잭션 영수증 대기
        Optional<TransactionReceipt> transactionReceipt = Optional.empty();
        while (!transactionReceipt.isPresent()) {
            EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transactionHash).send();
            transactionReceipt = receipt.getTransactionReceipt();
            Thread.sleep(3000);  // 3초 간격으로 확인
        }

        return transactionReceipt.get();
    }
}