package org.ssafy.d210._common.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ssafy.d210._common.request.MessageInfo;
import org.ssafy.d210.members.entity.Members;
import org.ssafy.d210.members.entity.MsgType;
import org.ssafy.d210.members.entity.VoiceMessage;
import org.ssafy.d210.members.repository.MembersRepository;
import org.ssafy.d210.members.repository.VoiceMessageRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocketService {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations template;
    private final S3Base64Uploader s3Base64Uploader;
    private final S3MultiPartUploader multiPartUploader;
    private final VoiceMessageRepository voiceMessageRepository;
    private final MembersRepository membersRepository;

    /*
    * A. 입장 메세지 관리
    *   1) 입장 시 채팅방 인원 수 증가
    *   2) TTS 변환
    *   3) 변환된 TTS를 S3에 저장후 URL로 받기
    *   4) URL도 메세지 DTO에 더하기
    *   5) 메세지 전송
    *
    *   -> 인자: 직렬화된 메세지 객체
    * */

    @Transactional
    public void sendEnterMessage(String publishMessage){
        try {
            MessageInfo msg = objectMapper.readValue(publishMessage, MessageInfo.class);


            // A. 들어온 메세지 확인
            log.info("들어온 입장 메세지: \n" +
                    "보낸 이: "+ msg.getSenderId() +
                    "받는 이: "+ msg.getReceiverId()
            );

            // B. 메세지 내용 채우기
            msg.setTextContent(msg.getSenderId()+"님 환영합니다!");
            msg.setCreatedAt(LocalDateTime.now());
            msg.setOpened(false);


            saveAndSendingMessageToSubscriber(msg);

        } catch (Exception e){
            log.error("Exception {}",e.getMessage());
        }
    }


    @Transactional
    public void sendTalkMessage(String publishMessage){
        try {
            MessageInfo msg = objectMapper.readValue(publishMessage, MessageInfo.class);


            // A. 들어온 메세지 확인
            log.info("들어온 입장 메세지: " +
                    "\n 보낸 이: "+ msg.getSenderId() +
                    "\n 받는 이: "+ msg.getReceiverId() +
                    "\n 문자 타입: " + msg.getMessageType() +
                    "\n 문자 내용: " + msg.getTextContent() +
                    "\n VoiceURL: " + msg.getVoiceURL()
            );

            // B. 만약 들어온 메세지의 URL이 BASE64라면, http로 변환
            if(!msg.getVoiceURL().substring(0,4).equals("http")){
               String httpUrl =  s3Base64Uploader.Base64ToHttp(msg.getVoiceURL());
               msg.setVoiceURL(httpUrl);
            }

            saveAndSendingMessageToSubscriber(msg);

        } catch (Exception e){
            log.error("Exception {}",e.getMessage());
        }
    }

    @Transactional
    public void sendQuitMessage(String publishMessage) {
        try {
            MessageInfo msg = objectMapper.readValue(publishMessage, MessageInfo.class);


            // A. 들어온 메세지 확인
            log.info("들어온 입장 메세지: \n" +
                    "보낸 이: "+ msg.getSenderId() +
                    "받는 이: "+ msg.getReceiverId()
            );

            // B. 메세지 내용 채우기
            msg.setTextContent(msg.getSenderId()+"님이 퇴장하셨습니다!");
            msg.setCreatedAt(LocalDateTime.now());
            msg.setOpened(false);


            saveAndSendingMessageToSubscriber(msg);

        } catch (Exception e){
            log.error("Exception {}",e.getMessage());
        }
    }

    // Message EKsm
    public void  saveAndSendingMessageToSubscriber(MessageInfo msg ){

        // A. 메세지를 Entity로 바꿔서 DB에 저장
        Members sender = membersRepository.findById(msg.getSenderId()).orElse(null);
        Members receiver = membersRepository.findById(msg.getReceiverId()).orElse(null);


        voiceMessageRepository.save(
                VoiceMessage.toEntity(
                        sender,
                        receiver,
                        msg.getVoiceURL(),
                        true,
                        msg.getTextContent(),
                        msg.getMessageType().equals("VOICE")? MsgType.VOICE : MsgType.TTS
                ));


        // B. 메세지 전송
        template.convertAndSend("sub/member/"+msg.getReceiverId(), msg);
    }
}
