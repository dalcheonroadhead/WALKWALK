package org.ssafy.d210.wallets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.wallets._blockchain.entity.Receipt;
import org.ssafy.d210.wallets._blockchain.service.BlockchainService;
import org.ssafy.d210.wallets.dto.request.PutEggRequest;
import org.ssafy.d210.wallets.dto.request.PutMoneyRequest;
import org.ssafy.d210.wallets.dto.response.GetEggMoneyResponse;
import org.ssafy.d210.wallets.dto.response.GetWalletHistoryResponse;
import org.ssafy.d210.wallets.dto.response.PutEggResponse;
import org.ssafy.d210.wallets.dto.response.PutMoneyResponse;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.entity.WalletHistory;
import org.ssafy.d210.wallets.entity.WalletType;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;
import org.ssafy.d210.wallets.repository.WalletHistoryRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.ssafy.d210._common.exception.ErrorType.NOT_FOUND_MEMBER;
import static org.ssafy.d210._common.exception.ErrorType.NOT_FOUND_MEMBER_ACCOUNT;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WalletsService {

    private final MembersRepository membersRepository;
    private final MemberAccountRepository memberAccountRepository;
    private final WalletHistoryRepository walletHistoryRepository;

    private final BlockchainService blockchainService;

    public GetEggMoneyResponse getEggMoney(UserDetailsImpl userDetails) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return GetEggMoneyResponse.from(memberAccount);
    }

    public PutEggResponse putEggAdd(UserDetailsImpl userDetails, PutEggRequest putEggRequest) throws Exception {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        WalletHistory DBWalletHistory = walletHistoryRepository.save(WalletHistory.of(WalletType.EGG, true, putEggRequest.getPutEggValue(), "", LocalDateTime.now().plusHours(9), member));
        CompletableFuture<BigInteger> future = writeToBlockchain(DBWalletHistory, "맵 포인트 도달: 에그 획득");
        DBWalletHistory.updateReceiptId(String.valueOf(future.get()));  // get() 호출로 비동기 작업의 완료를 기다림

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest.getPutEggValue(), true));
    }

    public PutEggResponse putEggSub(UserDetailsImpl userDetails, PutEggRequest putEggRequest) throws Exception {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        WalletHistory DBWalletHistory = walletHistoryRepository.save(WalletHistory.of(WalletType.EGG, false, putEggRequest.getPutEggValue(), "", LocalDateTime.now().plusHours(9), member));
        CompletableFuture<BigInteger> future = writeToBlockchain(DBWalletHistory, "아이템 사용: 에그 차감");
        DBWalletHistory.updateReceiptId(String.valueOf(future.get()));  // get() 호출로 비동기 작업의 완료를 기다림

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest.getPutEggValue(), false));
    }

    public PutMoneyResponse putMoneyAdd(UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) throws Exception {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        WalletHistory DBWalletHistory = walletHistoryRepository.save(WalletHistory.of(WalletType.MONEY, true, putMoneyRequest.getPutMoneyValue(), "", LocalDateTime.now().plusHours(9), member));
        CompletableFuture<BigInteger> future = writeToBlockchain(DBWalletHistory, "목표 달성: 머니 획득");
        DBWalletHistory.updateReceiptId(String.valueOf(future.get()));  // get() 호출로 비동기 작업의 완료를 기다림

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public PutMoneyResponse putMoneySub(UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) throws Exception {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        WalletHistory DBWalletHistory = walletHistoryRepository.save(WalletHistory.of(WalletType.MONEY, false, putMoneyRequest.getPutMoneyValue(), "", LocalDateTime.now().plusHours(9), member));
        CompletableFuture<BigInteger> future = writeToBlockchain(DBWalletHistory, "머니 사용");
        DBWalletHistory.updateReceiptId(String.valueOf(future.get()));  // get() 호출로 비동기 작업의 완료를 기다림

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public List<GetWalletHistoryResponse> getWalletHistory(UserDetailsImpl userDetails) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());

        return walletHistoryRepository.findAllByMember(member)
                .stream().map(GetWalletHistoryResponse::from)
                .collect(Collectors.toList());
    }

    @Async
    public CompletableFuture<BigInteger> writeToBlockchain(WalletHistory walletHistory, String description) throws Exception {
        BigInteger receiptId = blockchainService.writeReceiptToBlockchain(
                Receipt.of(
                        walletHistory.getWalletType().name(),
                        walletHistory.getOperator(),
                        BigInteger.valueOf(walletHistory.getPrice()),
                        description,
                        walletHistory.getCreatedAt().toString(),
                        BigInteger.valueOf(walletHistory.getMember().getId()))
        );

        log.info(receiptId.toString());

        return CompletableFuture.completedFuture(receiptId);
    }

    public Members findByEmailAndDeletedAtIsNull(String email) {
        return membersRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER));
    }

    public MemberAccount findMemberAccountByMembers(Long memberAccountId) {
        return memberAccountRepository.findMemberAccountById(memberAccountId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ACCOUNT));
    }
}