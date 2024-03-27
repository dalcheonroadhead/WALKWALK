package org.ssafy.d210.wallets.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
    CA("스마트 컨트랙트 주소"),  // Contract Address
    RA("머니 충전 이력 주소"), // Recharge money Address
    EA("머니 환전 이력 주소"), // Exchange money Address
    WA("운동 이력 주소")  // Walk Address
    ;

    private final String BlockTypeName;
}