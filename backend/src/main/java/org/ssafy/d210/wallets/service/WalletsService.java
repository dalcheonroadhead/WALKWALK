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
import org.ssafy.d210.wallets.dto.request.PutHalleyGalleyMoneyRequest;
import org.ssafy.d210.wallets.dto.request.PutMoneyRequest;
import org.ssafy.d210.wallets.dto.response.GetEggMoneyResponse;
import org.ssafy.d210.wallets.dto.response.PutEggResponse;
import org.ssafy.d210.wallets.dto.response.PutHalleyGalleyMoneyResponse;
import org.ssafy.d210.wallets.dto.response.PutMoneyResponse;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;

import static org.ssafy.d210._common.exception.ErrorType.NOT_FOUND_MEMBER;
import static org.ssafy.d210._common.exception.ErrorType.NOT_FOUND_MEMBER_ACCOUNT;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WalletsService {

    private final MembersRepository membersRepository;
    private final MemberAccountRepository memberAccountRepository;

    public GetEggMoneyResponse getEggMoney(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return GetEggMoneyResponse.from(memberAccount);
    }

    public PutEggResponse putEggAdd(@AuthenticationPrincipal UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest, true));
    }

    public PutEggResponse putEggSub(@AuthenticationPrincipal UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest, false));
    }

    public PutMoneyResponse putMoneyAdd(@AuthenticationPrincipal UserDetailsImpl userDetails, PutMoneyRequest putMoneyRequest) {
        Members member = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return PutMoneyResponse.of(memberAccount.putMoney(putMoneyRequest.getPutMoneyValue(), true));
    }

    public PutHalleyGalleyMoneyResponse putHalleyGalleyMoney(@AuthenticationPrincipal UserDetailsImpl userDetails, PutHalleyGalleyMoneyRequest putHalleyGalleyMoneyRequest) {
        // 갈리 정보
        Members galley = findByEmailAndDeletedAtIsNull(userDetails.getMember().getEmail());
        MemberAccount galleyAccount = findMemberAccountByMembers(galley.getMemberAccountId().getId());

        // 할리 정보
        Members halley = findMembersById(putHalleyGalleyMoneyRequest.getHalleyId());
        MemberAccount halleyAccount = findMemberAccountByMembers(halley.getMemberAccountId().getId());

        // 할리 money 감소
        int halleyMoney = halleyAccount.putMoney(putHalleyGalleyMoneyRequest.getPutMoneyValue(), false);

        // 갈리 money 증가
        int galleyMoney = galleyAccount.putMoney(putHalleyGalleyMoneyRequest.getPutMoneyValue(), true);

        return PutHalleyGalleyMoneyResponse.of(halleyMoney, galleyMoney);
    }

    public Members findMembersById(Long halleyId) {
        return membersRepository.findMembersById(halleyId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER));
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