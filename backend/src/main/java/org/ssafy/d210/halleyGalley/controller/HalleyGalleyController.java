package org.ssafy.d210.halleyGalley.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.halleyGalley.dto.request.PostGalleyRequest;
import org.ssafy.d210.halleyGalley.dto.request.PostMissionRequest;
import org.ssafy.d210.halleyGalley.dto.request.PutGalleyResponseRequest;
import org.ssafy.d210.halleyGalley.service.HalleyGalleyService;
import org.ssafy.d210.halleyGalley.service.MissionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/halleygalley")
public class HalleyGalleyController {

    private final HalleyGalleyService halleyGalleyService;
    private final MissionService missionService;

    @PostMapping("/galley-request")
    public ApiResponseDto<?> postGalleyRequest(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostGalleyRequest request){
        return ApiResponseDto.of(MsgType.POST_GALLEY_REQUEST_SUCCESSFULLY, halleyGalleyService.postGalleyRequest(userDetails.getMember(), request));
    }

    @PutMapping("/galley-response")
    public ApiResponseDto<?> putGalleyResponse(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PutGalleyResponseRequest request){
        return ApiResponseDto.of(MsgType.PUT_GALLEY_RESPONSE_SUCCESSFULLY, halleyGalleyService.putGalleyResponse(userDetails.getMember(), request));
    }

    @GetMapping("/halley-to-galley")
    public ApiResponseDto<?> getGalleyList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_GALLEY_LIST_SUCCESSFULLY, halleyGalleyService.getGalleyList(userDetails.getMember()));
    }

    @GetMapping("/galley-to-halley")
    public ApiResponseDto<?> getHalleyList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_HALLEY_LIST_SUCCESSFULLY, halleyGalleyService.getHalleyList(userDetails.getMember()));
    }

    @GetMapping("/halley-request-list")
    public ApiResponseDto<?> getHalleyRequestList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_HALLEY_REQUEST_LIST_SUCCESSFULLY, halleyGalleyService.getHalleyRequestList(userDetails.getMember()));
    }

    @PostMapping("/mission")
    public ApiResponseDto<?> postMission(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostMissionRequest request){
        return ApiResponseDto.of(MsgType.POST_MISSION_SUCCESSFULLY, missionService.postMission(userDetails.getMember(), request));
    }

    @GetMapping("/halley")
    public ApiResponseDto<?> getGalley(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long memberId){
        return ApiResponseDto.of(MsgType.GET_HALLEY_SUCCESSFULLY, halleyGalleyService.getHalley(userDetails.getMember(), memberId));
    }

    @GetMapping("/galley")
    public ApiResponseDto<?> getHalley(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long memberId){
        return ApiResponseDto.of(MsgType.GET_GALLEY_SUCCESSFULLY, halleyGalleyService.getGalley(userDetails.getMember(), memberId));
    }
}
