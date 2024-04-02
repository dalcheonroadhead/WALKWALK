package org.ssafy.d210.wallets._blockchain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.wallets.service.WalletsService;

import static org.ssafy.d210._common.response.MsgType.GET_RECEIPT_SUCCESSFULLY;

@Slf4j
@RestController
@RequestMapping("/api/blockchain")
@RequiredArgsConstructor
public class BlockchainController {

    private final WalletsService walletsService;

    @GetMapping("/receipt/{wallet-history-id}")
    public ApiResponseDto<?> getReceipt(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "wallet-history-id") Long walletHistoryId) {
        log.info("BlockchainController.getReceipt");
        return ApiResponseDto.of(GET_RECEIPT_SUCCESSFULLY, walletsService.getReceipt(userDetails, walletHistoryId));
    }
}
