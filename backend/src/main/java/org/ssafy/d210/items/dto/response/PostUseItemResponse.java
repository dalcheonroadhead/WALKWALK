package org.ssafy.d210.items.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUseItemResponse {
    private Integer money;

    @Builder
    private PostUseItemResponse(Integer money) {
        this.money = money;
    }

    public static PostUseItemResponse of(Integer money) {
        return builder()
                .money(money)
                .build();
    }
}