package org.ssafy.d210.members.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.exception.ErrorType;
import org.ssafy.d210._common.response.oauth2Google.GoogleAccessTokenInfo;
import org.ssafy.d210._common.response.oauth2Google.GoogleOauthTokenInfo;
import org.ssafy.d210._common.response.oauth2Google.GoogleProfileInfo;
import org.ssafy.d210._common.response.oauth2Google.GoogleRefreshTokenInfo;
import org.ssafy.d210._common.service.jwt.JwtUtil;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.entity.Role;
import org.ssafy.d210.members.repository.MembersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MembersRepository membersRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Value("${google.client-id}")
    String client_id;
    @Value("${google.client-secret}")
    String client_secret;

    // A. 명세와 구현의 분리
    public GoogleOauthTokenInfo getAccessToken(String code, String redirectUri) {

        System.out.println(client_id);
        System.out.println(client_secret);

        return requestAccessToken(code, redirectUri);
    }


    // B. 실제 구현 - AccessToken 얻기
    private GoogleOauthTokenInfo requestAccessToken(String code, String redirectUri) {
        // B-1) 메타 데이터 작성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // B-2) Params 작성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", client_id);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", client_secret);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // B-3) AccessToken 요청 후 결과 값
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        try {
            // B-4) 직렬화된 JSON을 진짜 객체 형태로 역직렬화, 실패시 FAIL_ON_UNKNOWN_PROPERTIES 에러가 나고, 이는 DTO 명세를 맞추지 못한 것이다.
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return objectMapper.readValue(responseEntity.getBody(), GoogleOauthTokenInfo.class);
        } catch (JsonProcessingException e) {
            // B-5) 에러 내역 참고
            log.info(e.getMessage());
            return null;
        }
    }

    // C.   구글 [Resource Server]에서 가져온 데이터와 일치하는 유저가 없다면 해당 유저 Entity 채워서 DB에 저장
    //      만약 있다면 [WALK_WALK TOKEN]을 만들어서 전해줌.
    //      따라서 내 로직은 요청이 들어올 때마다 [WALK_WALK] [AccessToken]과 [RefreshToken]이 같이 만들어진다.
    public List<String> SaveUserAndGetToken(
            String token, HttpServletResponse response, GoogleAccessTokenInfo gat, GoogleRefreshTokenInfo grt) {

        // C-1) AT 이용해 유저 정보 받아온다.
        GoogleProfileInfo googleProfileInfo = findProfile(token);
        log.info("{}", googleProfileInfo.toString());

        // C-2) 해당 정보를 가진 유저가 있는지 찾는다.
        Members member = membersRepository.findByEmailAndDeletedAtIsNull(googleProfileInfo.getEmail()).orElse(null);

        // C-3) user가 Null이면 response에 Error를 띄워보내야 해서 이렇게 씀
        boolean isNew = false;

        // C-4) 아예 처음 가입한 사람인 경우 다 비어있을 것이다. 그러면 신규 유저다.
        if(member == null){
            member = Members.of(
                    googleProfileInfo.getEmail(),
                    googleProfileInfo.getName(),
                    googleProfileInfo.getPicture(),
                    Role.USER
            );
            isNew = true;
        }

        else if(member.getBirthYear() == null || member.getBlockAddresses() == null){
            isNew = true;
        }
        else{
            member.setNew(true);
        }

        membersRepository.save(member);

        String jwtAccessToken = jwtUtil.createToken(member,false,gat,grt);
        String jwtRefreshToken = jwtUtil.createToken(member,true,gat,grt);
        response.addHeader("Authorization", jwtAccessToken);
        response.addHeader("refresh_token",jwtRefreshToken);

        List<String> ans = new ArrayList<>();
        ans.add(jwtAccessToken);
        ans.add(String.valueOf(isNew));
        ans.add(jwtRefreshToken);

        return ans;
    }


    // D. [Google AccessToken]으로 원하는 정보 가져오기
    public GoogleProfileInfo findProfile(String token) {

        // D-1) RestTemplate 작성
        RestTemplate rt = new RestTemplate();

        // D-2) Metadata 작성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token); //(1-4)
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // D-3) Entity 객체에 하나의 값으로 header를 넣는다. -> 왜 넣는지는 공부 필요
        HttpEntity<MultiValueMap<String, String>> googleProfileRequest =
                new HttpEntity<>(headers);

        // D-4) Http 요청 (POST 방식) 후, response 변수에 응답을 받음
        ResponseEntity<String> googleProfileResponse = rt.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                googleProfileRequest,
                String.class
        );

        // D-5)
        ObjectMapper objectMapper = new ObjectMapper();
        GoogleProfileInfo googleProfileInfo = null;

        try {
            googleProfileInfo = objectMapper.readValue(googleProfileResponse.getBody(),GoogleProfileInfo.class);
        }catch (JsonProcessingException e){
            log.error(e.getMessage());
        }

        return googleProfileInfo;
    }


    public Members validateMemberByEmail(String email) {
        return membersRepository.findByEmailAndDeletedAtIsNull(email)
                .orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_MEMBER));
    }

}
