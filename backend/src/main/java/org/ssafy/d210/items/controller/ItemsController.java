package org.ssafy.d210.items.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210._common.service.UserDetailsImpl;
import org.ssafy.d210.items.dto.request.PostUseItemRequest;
import org.ssafy.d210.items.service.ItemsService;

import static org.ssafy.d210._common.response.MsgType.GET_ITEM_LIST_SUCCESSFULLY;
import static org.ssafy.d210._common.response.MsgType.POST_ITEM_USE_SUCCESSFULLY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemsController {

    private final ItemsService itemsService;

    @GetMapping("/")
    public ApiResponseDto<?> getList() {
        log.info("ItemsController.getList");
        return ApiResponseDto.of(GET_ITEM_LIST_SUCCESSFULLY, itemsService.getList());
    }

    @PostMapping("/use")
    public ApiResponseDto<?> postUseItem(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid PostUseItemRequest postUseItemRequest) {
        log.info("ItemsController.postUseItem");
        return ApiResponseDto.of(POST_ITEM_USE_SUCCESSFULLY, itemsService.useItem(userDetails, postUseItemRequest));
    }
}
