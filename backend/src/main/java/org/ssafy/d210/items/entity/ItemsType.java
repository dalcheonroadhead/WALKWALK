package org.ssafy.d210.items.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemsType {
    STREAK("스트릭 아이템");

    private final String itemsTypeName;

}
