package org.ssafy.d210.halleyGalley.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.halleyGalley.service.HalleyGalleyService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/halleygalley")
public class HalleyGalleyController {

    private final HalleyGalleyService halleyGalleyService;

    @GetMapping("/halley-to-galley")
    public ApiResponseDto<?> getGalleyList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_GALLEY_LIST_SUCCESSFULLY, halleyGalleyService.getGalleyList(userDetails.getMember()));
    }

    @GetMapping("/galley-to-halley")
    public ApiResponseDto<?> getHalleyList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_HALLEY_LIST_SUCCESSFULLY, halleyGalleyService.getHalleyList(userDetails.getMember()));
    }
}
