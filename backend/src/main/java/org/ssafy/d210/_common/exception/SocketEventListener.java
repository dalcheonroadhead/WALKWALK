package org.ssafy.d210._common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class SocketEventListener {


    @EventListener
    public void connectEvent(SessionConnectEvent sessionConnectEvent){
        log.info("Socket 연결됨! ദ്ദി´-ω-`) 연결된 사용자 정보={}", sessionConnectEvent);
    }

    @EventListener
    public void webSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent){
        log.warn("Socket 연결 종료 ＿〆(。。) 종료 이벤트={}", sessionDisconnectEvent);

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());

        log.warn("Socket 종료 이벤트의 Header={}", headerAccessor);
    }


}
