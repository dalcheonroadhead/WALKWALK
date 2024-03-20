package org.ssafy.d210._common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.repository.MembersRepository;

/*
 * (〜￣△￣)〜   인증 객체 구현하는 로직       〜(￣△￣〜)
 * 현재 인증된 사용자의 DB를 조회하여,
 * 정보를 가져와서 전역에서 사용할 수 있는 인증 객체 UserDetails 를 만든다.
 * */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MembersRepository membersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Members member = membersRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorType.NOT_FOUND_USER.getMsg()));


        return new UserDetailsImpl(member, member.getEmail());
    }
}
