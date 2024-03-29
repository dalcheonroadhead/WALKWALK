package org.ssafy.d210._common.service.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ssafy.d210._common.exception.ErrorType;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final   JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // A. request 에 담긴 토큰의 실제 부분(Bearer 뺀 부분)을 가져온다.
        String token = jwtUtil.resolveToken(request);

        // B. 토큰이 null 이면 다음 필터로 넘어간다
        if (token == null) {

            filterChain.doFilter(request, response);
            return;
        }

        // C. 토큰이 유효하지 않으면 Exception 체크하고 다음 필터로 넘어간다

        int validationCheck = jwtUtil.validateToken(token);

        if (validationCheck < 0) {
            if(validationCheck == -1) {
                request.setAttribute("exception", ErrorType.NOT_VALID_TOKEN.toString());
                filterChain.doFilter(request, response);
                return;
            }else if (validationCheck == -2){
                request.setAttribute("exception", ErrorType.EXPIRED_TOKEN.toString());
            }
        }

        // D. 유효한 토큰이라면, 토큰으로부터 사용자 정보를 가져온다.
        Claims info = jwtUtil.getUserInfoFromToken(token);  // 토큰 해부

        log.info("토큰에 들어있는 값={}",info.toString());


        try {

            setAuthentication(info.getSubject());   // 이메일로 사용자 정보를 얻어오고, 그 사용자 정보로 인증 객체 만든다.

        } catch (UsernameNotFoundException e) {
            request.setAttribute("exception", ErrorType.NOT_FOUND_MEMBER.toString());
            log.error("관련에러: {}", e.getMessage());
        }
        // 다음 필터로 넘어간다.
        filterChain.doFilter(request, response);

    }

    private void setAuthentication(String userEmail) {

        SecurityContext context = SecurityContextHolder.createEmptyContext();   // 서버에서 인증 객체용 [Thread]를 만듬
        Authentication authentication = jwtUtil.createAuthentication(userEmail); // 인증 객체 만들기
        context.setAuthentication(authentication);  // 해당 [Thread]에 객체를 기록

        SecurityContextHolder.setContext(context); // [Thread]를 서버 전체 컨테이너에 기록
    }
}
