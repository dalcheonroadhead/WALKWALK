package org.ssafy.d210._common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.service.S3Base64Uploader;
import org.ssafy.d210._common.service.S3MultiPartUploader;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SocketController {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations template;
    private final S3Base64Uploader s3Base64Uploader;
    private final S3MultiPartUploader multiPartUploader;



}
