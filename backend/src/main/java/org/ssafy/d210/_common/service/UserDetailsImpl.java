package org.ssafy.d210._common.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.ssafy.d210.members.entity.Members;

import java.util.ArrayList;
import java.util.Collection;

/*
 * (〜￣△￣)〜   인증 객체 -> [UserDetailsServiceImpl]에 의 구현      〜(￣△￣〜)
 * Authentication 객체가 [Security Context]에 적히면 그 내용 가지고 기록
 * 따라서 해당 [UserDetailsImpl]를 사용하면 인증된 객체를 서버 전역에서 쓸 수 있다.
 * */

public class UserDetailsImpl implements UserDetails {

    private final Members member;
    private final String googleEmail;

    public UserDetailsImpl(Members member, String googleEmail) {
        this.member = member;
        this.googleEmail = googleEmail;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        Collection<GrantedAuthority> authorities = new ArrayList<>();   // 사용자 권한을 GrantedAuthority 로 추상화
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
