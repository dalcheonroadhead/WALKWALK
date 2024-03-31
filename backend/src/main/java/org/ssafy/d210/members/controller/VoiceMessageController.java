package org.ssafy.d210.members.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210.members.service.VoiceMessageService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/members")
public class VoiceMessageController {

    private VoiceMessageService voiceMessageService;

    @GetMapping("/load/{member-id}")
    public ApiResponseDto<?> loadVoiceMessage(
            @PathVariable(value = "member-id") long memberId,
            @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
            @RequestParam(required = false, defaultValue = "createdAt", value = "criteria") String criteria
    ){
        return ResponseUtils.ok(voiceMessageService.loadVoiceMessage(memberId,pageNo,criteria), MsgType.SEARCH_SUCCESSFULLY);
    }


}
