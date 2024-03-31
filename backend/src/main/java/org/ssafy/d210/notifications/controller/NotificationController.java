package org.ssafy.d210.notifications.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.response.MsgType;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.notifications.dto.request.PutNotificationRequest;
import org.ssafy.d210.notifications.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping(value = "/subscribe")
    public ApiResponseDto<?> subscribe(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ApiResponseDto.of(MsgType.SUBSCRIBE_SUCCESSFULLY, notificationService.subscribe(userDetails.getMember().getId()));
    }

    @GetMapping("/")
    public ApiResponseDto<?> getAllNotifications(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ApiResponseDto.of(MsgType.GET_NOTIFICATION_LIST_SUCCESSFULLY, notificationService.getAllNotifications(userDetails.getMember()));
    }

    @PutMapping("/")
    public ApiResponseDto<?> putNotifications(@RequestBody PutNotificationRequest request){
        return ApiResponseDto.of(MsgType.PUT_NOTIFICATION_SUCCESSFULLY, notificationService.putNotifications(request));
    }
}
