package org.ssafy.d210.wallets.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.exception.CustomException;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.wallets.dto.request.PutMoneyRequest;
import org.ssafy.d210.wallets.dto.request.PutEggRequest;
import org.ssafy.d210.wallets.service.WalletsService;

import static org.ssafy.d210._common.exception.ErrorType.BAD_REQUEST;
import static org.ssafy.d210._common.response.MsgType.*;
import static org.ssafy.d210._common.utils.BindingResultUtils.getErrorMessages;

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

    @PutMapping("/egg-add")
    public ApiResponseDto<?> putEggAdd(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid PutEggRequest putEggRequest, BindingResult bindingResult) throws Exception {
        log.info("WalletsController.putEggAdd");

        // validation 오류
        if (bindingResult.hasErrors()) {
            throw new CustomException(BAD_REQUEST, getErrorMessages(bindingResult));
        }

        return ApiResponseDto.of(PUT_EGG_ADD_SUCCESSFULLY, walletsService.putEggAdd(userDetails, putEggRequest));
    }

    @PutMapping("/egg-sub")
    public ApiResponseDto<?> putEggSub(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid PutEggRequest putEggRequest, BindingResult bindingResult) throws Exception {
        log.info("WalletsController.putEggSub");

        // validation 오류
        if (bindingResult.hasErrors()) {
            throw new CustomException(BAD_REQUEST, getErrorMessages(bindingResult));
        }

        return ApiResponseDto.of(PUT_EGG_SUB_SUCCESSFULLY, walletsService.putEggSub(userDetails, putEggRequest));
    }

    @PutMapping("/money-add")
    public ApiResponseDto<?> putMoneyAdd(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid PutMoneyRequest putMoneyRequest, BindingResult bindingResult) throws Exception {
        log.info("WalletsController.putMoneyAdd");

        // validation 오류
        if (bindingResult.hasErrors()) {
            throw new CustomException(BAD_REQUEST, getErrorMessages(bindingResult));
        }

        return ApiResponseDto.of(PUT_MONEY_ADD_SUCCESSFULLY, walletsService.putMoneyAdd(userDetails, putMoneyRequest));
    }

    @PutMapping("/money-sub")
    public ApiResponseDto<?> putMoneySub(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid PutMoneyRequest putMoneyRequest, BindingResult bindingResult) throws Exception {
        log.info("WalletsController.putMoneySub");

        // validation 오류
        if (bindingResult.hasErrors()) {
            throw new CustomException(BAD_REQUEST, getErrorMessages(bindingResult));
        }

        return ApiResponseDto.of(PUT_MONEY_SUB_SUCCESSFULLY, walletsService.putMoneySub(userDetails, putMoneyRequest));
    }

    @PutMapping("/money-halley-galley")
    public ApiResponseDto<?> putHalleyGalleyMoney(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid PutMoneyRequest putMoneyRequest, BindingResult bindingResult) throws Exception {
        log.info("WalletsController.putHalleyGalleyMoney");

        // validation 오류
        if (bindingResult.hasErrors()) {
            throw new CustomException(BAD_REQUEST, getErrorMessages(bindingResult));
        }

        return ApiResponseDto.of(PUT_MONEY_HALLEY_TO_GALLEY_SUCCESSFULLY, walletsService.putMoneyAdd(userDetails, putMoneyRequest));
    }

    @GetMapping("/wallet-history")
    public ApiResponseDto<?> getWalletHistory(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("WalletsController.getWalletHistory");
        return ApiResponseDto.of(GET_WALLET_HISTORY_SUCCESSFULLY, walletsService.getWalletHistory(userDetails));
    }
}