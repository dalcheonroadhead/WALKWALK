package org.ssafy.d210.wallets.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.wallets.service.WalletsService;

import static org.ssafy.d210._common.response.MsgType.GET_EGG_MONEY_SUCCESSFULLY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallets")
public class WalletsController {

    private final WalletsService walletsService;

    @GetMapping("/egg-money")
    public ApiResponseDto<?> getEggMoney(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("WalletsController.getEggMoney");
        return ApiResponseDto.of(GET_EGG_MONEY_SUCCESSFULLY, walletsService.getEggMoney(userDetails));
    }
}
