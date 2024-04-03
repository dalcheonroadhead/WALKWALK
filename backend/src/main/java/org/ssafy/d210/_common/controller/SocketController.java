package org.ssafy.d210._common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.service.S3Base64Uploader;
import org.ssafy.d210._common.service.S3MultiPartUploader;
import org.ssafy.d210._common.service.SocketService;

@Slf4j
@RestController
@RequiredArgsConstructor

public class SocketController {

    public final SocketService socketService;

    /* A    유저가 소켓 연결 및 구독 신청 하면 자동으로 입장 메세지가 보내지도록 프론트에서 처리
            여기서 입장 메세지를 관리

            pub/chat/enterUser -> 로 send 요청을 보내면 일로 온다.
            chat/enterUser 가 하나의 TOPIC 이다.

     */

    // 1. 입장하였습니다 메세지 용
    @MessageMapping("/api/socket/enter")
    public void enterMsg(String publishMessage, SimpMessageHeaderAccessor headerAccessor){
        log.info("입장 메시지 Header: {}", headerAccessor);

        socketService.sendEnterMessage(publishMessage);
    }


    // 2. 일반 대화 용
    @MessageMapping("/api/socket/talk")
    public void talkMsg(String publishMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("입장 메시지 Header: {}", headerAccessor);

        socketService.sendTalkMessage(publishMessage);
    }

    // 3. 퇴장 메세지 용

    @MessageMapping("/api/socket/quit")
    public void quitMsg(String publishMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("입장 메시지 Header: {}", headerAccessor);

        socketService.sendQuitMessage(publishMessage);
    }

    // 4. 어드민 용: 어드민 등록: 전수민(1), 김세현(3), 심규영(7)
    // 어드민 용 메세지 별도 처리할 필요 있을 경우, 구현 예정!
    @MessageMapping("/api/socket/admin")
    public void adminMsg(String publishMessage, SimpMessageHeaderAccessor headerAccessor) {
        log.info("입장 메시지 Header: {}", headerAccessor);

        socketService.sendTalkMessage(publishMessage);
    }

}
