package org.ssafy.d210._common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.ssafy.d210.members.repository.MembersRepository;

import java.security.AuthProvider;
import java.util.Map;


/*
* 해당 Service의 loadUser는 Provider로부터 access Token을 얻은 후에 실행된다.
* */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MembersRepository membersRepository;
    private static final String GOOGLE = "google";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        log.info("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");

        // 1)
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 2)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // OAuth2 로그인 시 키(PK)가 되는 값
        Map<String, Object> attributes = oAuth2User.getAttributes();                    // 소셜 로그인에서 API가 제공하는 userInfo의 Json 값(유저 정보들)
        String oauth2AccessToken = userRequest.getAccessToken().getTokenValue();        // Token 받기






        return null;
    }



}
/*
*   1)
*   DefaultOAuth2UserService rorcpfmf todtjd, loadUser(userRequest)를 통해
*   DefaultOAuth2User 객체를 생성 후 반환
*   DefaultOAuth2UserService의 loadUser()는 소셜 로그인 API의 사용자 정보 제공 URI로 요청을 보내서
*   사용자 정보를 얻은 후, 이를 통해 DefaultOAuth2User 객체를 생성 후 반환한다.
*   결과적으로, OAuth2User는 OAuth2 서비스에서 가져온 유저 정보를 담고 있는 유저임
* */