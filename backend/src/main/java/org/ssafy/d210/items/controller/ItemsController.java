package org.ssafy.d210.items.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssafy.d210._common.response.ApiResponseDto;
import org.ssafy.d210.items.service.ItemsService;

import static org.ssafy.d210._common.response.MsgType.GET_ITEM_LIST_SUCCESSFULLY;

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
}
