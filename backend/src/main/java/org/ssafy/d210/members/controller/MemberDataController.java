package org.ssafy.d210.members.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.response.ResponseUtils;
import org.ssafy.d210.members.dto.request.AdditionalInfo;
import org.ssafy.d210.members.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberDataController {

    private final MemberService memberService;
    @GetMapping("/check-duplicated")
    public ApiResponseDto<Map<String, Boolean>> checkDuplicated (@RequestParam("nickname")String nickname) {
        boolean isDuplicated = memberService.isDuplicatedID(nickname);

        Map<String, Boolean> ret = new HashMap<>();
        ret.put("isDuplicated", isDuplicated);

        log.info("중복 확인 완료 {}의 중복 여부는 {} 입니다. ξ´-ﻌ-`Ҙ", nickname, isDuplicated);
        return ResponseUtils.ok(ret, MsgType.SEARCH_SUCCESSFULLY);
    }

    @PostMapping("/")
    public ApiResponseDto<Map<String, String>> postAdditionalInfo (@RequestBody AdditionalInfo addInfo) {

        log.info("객체 바인딩이 잘 되었을까요? 바인딩된 객체는 {}",addInfo);

        return null;
    }
}
