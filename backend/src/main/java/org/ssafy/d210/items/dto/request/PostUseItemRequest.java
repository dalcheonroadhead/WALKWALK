package org.ssafy.d210.items.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PostUseItemRequest {

    @NotNull(message = "사용할 아이템의 id를 입력해주세요.")
    private Long itemId;
}
