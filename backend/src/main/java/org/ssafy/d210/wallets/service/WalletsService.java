package org.ssafy.d210.wallets.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.wallets.dto.response.GetEggMoneyResponse;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;

import java.util.Optional;

import static org.ssafy.d210._common.exception.ErrorType.MEMBER_ACCOUNT_NOT_FOUND;
import static org.ssafy.d210._common.exception.ErrorType.MEMBER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletsService {

    private MembersRepository membersRepository;
    private MemberAccountRepository memberAccountRepository;

    public GetEggMoneyResponse getEggMoney(Members members) {
        findMembersById(members.getId());

        Optional<MemberAccount> optionalMemberAccount = memberAccountRepository.findMemberAccountById(members.getMemberAccountId().getId());
        if (!optionalMemberAccount.isPresent()) {
            throw new CustomException(MEMBER_ACCOUNT_NOT_FOUND);
        }

        return GetEggMoneyResponse.of(optionalMemberAccount.get());
    }

    private Members findMembersById(Long membersId) {
        return membersRepository.findMembersById(membersId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }
}
