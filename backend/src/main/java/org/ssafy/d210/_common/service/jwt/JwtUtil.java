package org.ssafy.d210._common.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.ssafy.d210._common.response.oauth2Google.GoogleAccessTokenInfo;
import org.ssafy.d210._common.response.oauth2Google.GoogleRefreshTokenInfo;
import org.ssafy.d210._common.service.UserDetailsServiceImpl;
import org.ssafy.d210.members.entity.Members;

import java.lang.reflect.Member;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Environment env;

    private final UserDetailsServiceImpl userDetailsService;
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String REFRESH_TOKEN = "Refresh_token";
    private static final String BEARER_PREFIX = "Bearer ";

    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // A. JWT 서명에 쓸 알고리즘

    @PostConstruct // B. 객체 생성 이후에 바로 동작
    public void init() {
        byte [] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // C. 요청 Header에서 토큰 가져와서 Bearer 접두사 때기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){
            return bearerToken.substring(7);
        }
        return null;
    }

    /* D. 토큰 만들기
    *  사용자의 계정 인증을 대변하는 WALK_WALK_JWT 토큰에는
    *  Google Access Token, Google Refresh Token, 각 토큰의 정보가 들어간다.
    *  우리는 해당 토큰을 다시 분해해서 각 사용자의 AT, RT 남은 시각을 계산해, 미리 AT를 재발급 받아도 된다.
    * */
    public String createToken (Members member, boolean isRT ){

        int TOKEN_TIME = 0;

        // 해당 값으로 [AccessToken]을 만들 것인지, [RefreshToken]을 만들 것인지 확인한다.
        if(!isRT){
            TOKEN_TIME = Integer.parseInt(Objects.requireNonNull(env.getProperty("jwt.token.access-expiration-time")));
        }else{
            TOKEN_TIME = Integer.parseInt(Objects.requireNonNull(env.getProperty("jwt.token.refresh-expiration-time")));
        }

        Date now = new Date();


        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(member.getEmail())  // 이메일로 token의 주인을 찾고 정보 얻어올 것이다.
                        .setIssuedAt(new Date(now.getTime()))
                        .setExpiration(new Date(now.getTime() + TOKEN_TIME))
                        .signWith(key, signatureAlgorithm)  // 전자 서명
                        .compact();
    }

    // E. 토큰 검증하기
    public int validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return 1;
        } catch (io.jsonwebtoken.security.SignatureException | SecurityException | MalformedJwtException e){
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
            log.error("관련에러: {}", e.getMessage());
            return -1;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
            log.error("관련에러: {}", e.getMessage());
            return -1;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
            log.error("관련에러: {}", e.getMessage());
            return -1;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
            log.error("관련에러: {}", e.getMessage());
            return -2;
        }
    }

    // F. 토큰 해부해서 사용자 정보 다시 빼내기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // E. [userEmail]을 받아서 [DB]에서 정보를 가져와 인증 객체 생성
    public Authentication createAuthentication(String userEmail) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
