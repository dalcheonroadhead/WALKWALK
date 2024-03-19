package org.ssafy.d210.wallets.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {
    CA("스마트 컨트랙트 주소"),
    TA("용돈 거래 주소"),
    EA("운동 이력 주소")
    ;

    private final String BlockTypeName;
}