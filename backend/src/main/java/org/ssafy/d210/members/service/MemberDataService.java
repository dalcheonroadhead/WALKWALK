package org.ssafy.d210.members.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.members.dto.request.AdditionalInfo;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.wallets.entity.MemberAccount;
import org.ssafy.d210.wallets.repository.MemberAccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDataService {

    private final MembersRepository membersRepository;
    private final MemberAccountRepository memberAccountRepository;
    @Transactional
    public Members addAdditionalInfo (AdditionalInfo addInfo,  UserDetailsImpl userDetails) {

       long id = userDetails.getMember().getId();

       // 0) 추가 가입하려는 사용자의 객체 가져오기
       Members member = membersRepository.findById(id).orElse(null);

       // 1) 현재 [UserDetail]에 사용자가 없다.
       if(member == null){
           throw new CustomException(ErrorType.NOT_FOUND_MEMBER);
       }

       // 2) 사용자가 있으면 받은 값으로 세팅 -> dirty checking
        else{
            if(member.getMemberAccountId() == null){
                MemberAccount memberAccount = new MemberAccount();
                memberAccount.setEoa(addInfo.getEoa());
                memberAccount.setPublicKey(addInfo.getPublicKey());
                memberAccountRepository.save(memberAccount);
                member = addInfo.toEntity(member, memberAccount);
            }else {
                member.getMemberAccountId().setEoa(addInfo.getEoa());
                member.getMemberAccountId().setPublicKey(addInfo.getPublicKey());
                member = addInfo.toEntity(member, member.getMemberAccountId());
            }



       }

        return member;

    }
}
