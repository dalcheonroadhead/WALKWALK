package org.ssafy.d210.wallets._payment.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.wallets._payment.dto.request.PaymentApproveRequest;
import org.ssafy.d210.wallets._payment.dto.request.PaymentReadyRequest;
import org.ssafy.d210.wallets._payment.dto.response.PaymentApprove;
import org.ssafy.d210.wallets._payment.dto.response.PaymentReady;
import org.ssafy.d210.wallets._payment.service.PaymentService;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor

public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/kakaoPayReady")
    public ResponseEntity<PaymentReady> kakaoPayReady(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PaymentReadyRequest paymentReadyRequest) {
        PaymentReady paymentReady = paymentService.preparePayment(userDetails, paymentReadyRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(paymentReady.getTid())
                .toUri();

        return ResponseEntity.created(uri).body(paymentReady);
    }

    @PostMapping("/kakaoPayApprove")
    public ResponseEntity<PaymentApprove> kakaoPayApprove(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PaymentApproveRequest paymentApproveRequest) {
        PaymentApprove paymentApproveResponseDto = paymentService.approvePayment(userDetails, paymentApproveRequest);
        return ResponseEntity.ok(paymentApproveResponseDto);
    }

    @GetMapping("/approval")
    public String paymentApproval(@RequestParam("pg_token") String pgToken, HttpServletRequest httpServletRequest) {
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
