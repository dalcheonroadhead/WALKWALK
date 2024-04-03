package org.ssafy.d210.wallets.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WalletType {
    EGG("에그"),
    MONEY("머니");

    private final String WalletTypeName;
}