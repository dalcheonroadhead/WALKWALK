package org.ssafy.d210.wallets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.wallets._blockchain.dto.request.PostWriteToBlockchainRequest;
import org.ssafy.d210.wallets._blockchain.dto.response.GetReadFromBlockchainResponse;
import org.ssafy.d210.wallets._blockchain.dto.response.GetReceiptResponse;
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

import java.util.List;
import java.util.stream.Collectors;

import static org.ssafy.d210._common.exception.ErrorType.*;

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

    public PutEggResponse putEggAdd(UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        writeDBandBlockchain(WalletHistory.of(WalletType.EGG, true, putEggRequest.getPutEggValue(), "", member), "맵 포인트 도달: 에그 획득");

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest.getPutEggValue(), true));
    }

    public PutEggResponse putEggSub(UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        writeDBandBlockchain(WalletHistory.of(WalletType.EGG, false, putEggRequest.getPutEggValue(), "", member), "아이템 사용: 에그 차감");

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest.getPutEggValue(), false));
    }

    public PutMoneyResponse putMoneyAdd(UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        writeDBandBlockchain(WalletHistory.of(WalletType.MONEY, true, putMoneyRequest.getPutMoneyValue(), "", member), "목표 달성: 머니 획득");

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public PutMoneyResponse putMoneySub(UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        writeDBandBlockchain(WalletHistory.of(WalletType.MONEY, false, putMoneyRequest.getPutMoneyValue(), "", member), "머니 사용");

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public List<GetWalletHistoryResponse> getWalletHistory(UserDetailsImpl userDetails) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());

        return walletHistoryRepository.findAllByMember(member)
                .stream().map(GetWalletHistoryResponse::from)
                .collect(Collectors.toList());
    }

    public GetReceiptResponse getReceipt(UserDetailsImpl userDetails, Long walletHistoryId) {
        findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());

        String blockUri = findWalletHistoryById(walletHistoryId).getBlockUri();
        GetReadFromBlockchainResponse getReadFromBlockchainResponse = blockchainService.readFromBlockchain(blockUri);

        return GetReceiptResponse.from(getReadFromBlockchainResponse, blockUri);
    }

    public void writeDBandBlockchain(WalletHistory walletHistory, String description) {
        WalletHistory DBWalletHistory = walletHistoryRepository.save(walletHistory);

        PostWriteToBlockchainRequest postWriteToBlockchainRequest = PostWriteToBlockchainRequest.of(DBWalletHistory.getId(), walletHistory, description, DBWalletHistory.getCreatedAt());
        DBWalletHistory.updateBlockUri(blockchainService.writeToBlockchain(postWriteToBlockchainRequest));
    }

    public Members findByEmailAndDeletedAtIsNull(String email) {
        return membersRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER));
    }

    public MemberAccount findMemberAccountByMembers(Long memberAccountId) {
        return memberAccountRepository.findMemberAccountById(memberAccountId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ACCOUNT));
    }

    public WalletHistory findWalletHistoryById(Long id) {
        return walletHistoryRepository.findWalletHistoryById(id)
                .orElseThrow(() -> new CustomException(NOT_FOUND_WALLET_HISTORY));
    }
}