package org.ssafy.d210._common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.ssafy.d210._common.exception.SocketErrorHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    private SocketErrorHandler socketErrorHandler;

    // A. [Socket]이 열릴 주소를 정한다.
    //    접근 허용은 어떻게 할 것인지, SockJs를 통한 Http -> ws 변환도 허용할 것인지를 정한다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-stomp")
                .setAllowedOriginPatterns("*")
                .withSockJS();

        registry.setErrorHandler(socketErrorHandler);
    }

    // B. [TOPIC]에 대한 구독 요청과 발행 요청을 구분하는 접두사를 설정한다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //  B-1) 클라이언트에서 "sub/~~(특정 주소 A)"로 subscribe 요청을 보내면 해당 주소를 구독하게 된다.
        //       이제 주소 A로 메세지가 발행될 때마다, 구독 신청을 한 클라이언트는 메세지를 받아볼 수 있게 된다.
        //       신문 구독하는 거랑 시스템이 같다.
        registry.enableSimpleBroker("/sub");


        //  B-2) 클라이언트에서 "pub/~~ 특정주소 A"로 publish 요청을 보내게 되면,
        //       해당 주소 A로 메세지를 보내게 된다.
        //       해당 메세지는 구독 요청을 했던 모든 사람들에게 전달 된다.
        registry.setApplicationDestinationPrefixes("/pub");
    }

    // C. 메세지 크기가 default 메세지 크기를 넘어갈 경우를 대비하여, 메세지 크기 제한 최대치로 설정
    //    (해당 설정을 안하면, 메세지 크기가 BASE 64처럼 엄청 클 경우, WebSocket 통신이 끊긴다.)

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration){
        registration.setMessageSizeLimit(1024*1024*1024);
    }

    // D. C와 같은 이유에서 설정 -> Text 메세지가 들어오는 BufferSize를 21억까지 늘린다.
    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean(){
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(Integer.MAX_VALUE);
        container.setMaxBinaryMessageBufferSize(Integer.MAX_VALUE);
        return container;
    }

}
