package org.ssafy.d210.wallets._payment.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.wallets._payment.dto.request.PaymentApproveRequestDto;
import org.ssafy.d210.wallets._payment.dto.request.PaymentReadyRequestDto;
import org.ssafy.d210.wallets._payment.dto.response.PaymentApproveDto;
import org.ssafy.d210.wallets._payment.dto.response.PaymentReadyDto;
import org.ssafy.d210.wallets._payment.service.PaymentService;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor

public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/kakaoPayReady")
    public ResponseEntity<PaymentReadyDto> kakaoPayReady(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PaymentReadyRequestDto requestDto) {
        PaymentReadyDto paymentReadyDto = paymentService.preparePayment(userDetails, requestDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(paymentReadyDto.getTid())
                .toUri();

        return ResponseEntity.created(uri).body(paymentReadyDto);
    }

    @PostMapping("/kakaoPayApprove")
    public ResponseEntity<PaymentApproveDto> kakaoPayApprove(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PaymentApproveRequestDto requestDto) {
        PaymentApproveDto paymentApproveResponseDto = paymentService.approvePayment(userDetails, requestDto);
        return ResponseEntity.ok(paymentApproveResponseDto);
    }

    @GetMapping("/approval")
    public String paymentApproval(@RequestParam("pg_token") String pgToken, HttpServletRequest request) {
        return "redirect:/kakaopay-approval";
    }

    @GetMapping("/fail")
    public String paymentFail() {
        return "redirect:/kakaopay-fail";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "redirect:/kakaopay-cancel";
    }
}
