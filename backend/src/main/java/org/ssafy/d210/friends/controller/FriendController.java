package org.ssafy.d210.friends.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.friends.dto.request.PostFriendRequest;
import org.ssafy.d210.friends.dto.request.PutFriendRequest;
import org.ssafy.d210.friends.service.FriendService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/{memberId}")
    public ApiResponseDto<?> getMemberInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long memberId){
        return ApiResponseDto.of(MsgType.GET_MEMBER_INFO_SUCCESSFULLY, friendService.getMemberInfo(userDetails.getMember(), memberId));
    }

    @GetMapping("/")
    public ApiResponseDto<?> getMemberList(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_FRIEND_LIST_SUCCESSFULLY, friendService.getMemberList(userDetails.getMember()));
    }

    @PostMapping("/request")
    public ApiResponseDto<?> postFriend(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostFriendRequest postFriendRequest){
        return ApiResponseDto.of(MsgType.POST_FRIEND_REQUEST_SUCCESSFULLY, friendService.postFriend(userDetails.getMember(), postFriendRequest));
    }

    @PutMapping("/response")
    public ApiResponseDto<?> putFriend(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PutFriendRequest putFriendRequest){
        return ApiResponseDto.of(MsgType.PUT_FRIEND_REQUEST_SUCCESSFULLY, friendService.putFriend(userDetails.getMember(), putFriendRequest));
    }
}
