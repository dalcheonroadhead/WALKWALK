package org.ssafy.d210._common.exception;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.StompSubProtocolErrorHandler;

import java.nio.charset.StandardCharsets;

@Component
public class SocketErrorHandler extends StompSubProtocolErrorHandler {

    public SocketErrorHandler() {
        super();
    }

    @Override
    public Message<byte []> handleClientMessageProcessingError(Message<byte[]> clientMessage, Throwable ex){

        Throwable exception = new CustomException(ErrorType.TOKEN_DOESNT_EXIST);

        if(ex instanceof MessageDeliveryException){
            return handleUnauthorizedException(clientMessage, exception);
        }

        return super.handleClientMessageProcessingError(clientMessage, ex);
    }

    private Message<byte []> handleUnauthorizedException(Message<byte[]> clientMessage, Throwable ex){
        ErrorResponse errorResponse = ErrorResponse.of(401, "[Socket]을 보낸 이의 JWT 토큰에 문제가 있습니다.");

        return prepareErrorMessage(clientMessage, errorResponse, String.valueOf(errorResponse.getStatus()));
    }


    private Message<byte[]> prepareErrorMessage(Message<byte[]> clientMessage, ErrorResponse errorResponse, String errorCode){
        String message = errorResponse.getMsg();

        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.ERROR);

        accessor.setMessage(errorCode);
        accessor.setLeaveMutable(true);

        return MessageBuilder.createMessage(message.getBytes(StandardCharsets.UTF_8), accessor.getMessageHeaders());
    }
}
