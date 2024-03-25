package org.ssafy.d210.wallets.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.service.MemberService;
import org.ssafy.d210.wallets.dto.request.PutEggRequest;
import org.ssafy.d210.wallets.dto.response.GetEggMoneyResponse;
import org.ssafy.d210.wallets.dto.response.PutEggResponse;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;

import static org.ssafy.d210._common.exception.ErrorType.NOT_FOUND_MEMBER_ACCOUNT;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletsService {

    private MemberAccountRepository memberAccountRepository;
    private MemberService memberService;

    public GetEggMoneyResponse getEggMoney(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Members member = memberService.validateMemberByEmail(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return GetEggMoneyResponse.from(memberAccount);
    }

    public PutEggResponse putEggAdd(UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = memberService.validateMemberByEmail(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest, true));
    }

    public PutEggResponse putEggSub(UserDetailsImpl userDetails, PutEggRequest putEggRequest) {
        Members member = memberService.validateMemberByEmail(userDetails.getMember().getEmail());
        MemberAccount memberAccount = findMemberAccountByMembers(member.getMemberAccountId().getId());

        return PutEggResponse.of(memberAccount.putEgg(putEggRequest, false));
    }


    public MemberAccount findMemberAccountByMembers(Long memberAccountId) {
        return memberAccountRepository.findMemberAccountById(memberAccountId)
                .orElseThrow(() -> new CustomException(NOT_FOUND_MEMBER_ACCOUNT));
    }


}
