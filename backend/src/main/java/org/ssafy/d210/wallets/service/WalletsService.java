package org.ssafy.d210.wallets.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
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

    public GetEggMoneyResponse getEggMoney(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return GetEggMoneyResponse.from(memberAccount);
    }

    public PutEggResponse putEggAdd(@AuthenticationPrincipal UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        walletHistoryRepository.save(WalletHistory.of(WalletType.EGG, true, putEggRequest.getPutEggValue(), member));

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest.getPutEggValue(), true));
    }

    public PutEggResponse putEggSub(@AuthenticationPrincipal UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        walletHistoryRepository.save(WalletHistory.of(WalletType.EGG, false, putEggRequest.getPutEggValue(), member));

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest.getPutEggValue(), false));
    }

    public PutMoneyResponse putMoneyAdd(@AuthenticationPrincipal UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        walletHistoryRepository.save(WalletHistory.of(WalletType.MONEY, true, putMoneyRequest.getPutMoneyValue(), member));

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public PutMoneyResponse putMoneySub(@AuthenticationPrincipal UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        walletHistoryRepository.save(WalletHistory.of(WalletType.MONEY, false, putMoneyRequest.getPutMoneyValue(), member));

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public List<GetWalletHistoryResponse> getWalletHistory(UserDetailsImpl userDetails) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());

        return walletHistoryRepository.findAllByMember(member)
                .stream().map(GetWalletHistoryResponse::from)
                .collect(Collectors.toList());
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