package org.ssafy.d210._common.exception;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.ssafy.d210._common.service.jwt.JwtUtil;

/* （〜^∇^)〜 [SOCKET]으로 들어오기 전에 하는 handler 〜(^∇^〜） */

@RequiredArgsConstructor
@Component
public class SocketPreHandler implements ChannelInterceptor {

    private final JwtUtil jwtUtil;
    private static final String BEARER_PREFIX = "Bearer ";


    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        // A. 헤더 부분만 얻어내기
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

        // B. 헤더에서 토큰 얻어내기
        //    [Native Header]는 사용자가 수동 설정한 Header. 사용자가 만들지 않으면 생기지 않는다. Header가 커지면 성능 저하
        String authorizationHeader = String.valueOf(headerAccessor.getNativeHeader("Authorization"));

        // C. 토큰 없을 때 예외 처리
        if(authorizationHeader == null || authorizationHeader.equals("null")){
            throw new CustomException(ErrorType.TOKEN_DOESNT_EXIST);
        }

        // D. Bearer 자르고 Validation Check
        String token = authorizationHeader.substring(BEARER_PREFIX.length());

        int validationCheck = jwtUtil.validateToken(token);

        if(validationCheck < 0){
            if (validationCheck == -1){
                throw new CustomException(ErrorType.NOT_VALID_TOKEN);
            } else if (validationCheck == -2) {
                throw new CustomException(ErrorType.EXPIRED_TOKEN);
            }
        }


        return  message;
    }
}
