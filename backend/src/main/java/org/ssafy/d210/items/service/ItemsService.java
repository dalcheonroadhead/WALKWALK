package org.ssafy.d210.items.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ssafy.d210.items.dto.response.GetItemListResponse;
import org.ssafy.d210.items.repository.ItemsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public List<GetItemListResponse> getList() {
        return itemsRepository.findAll()
                .stream().map(GetItemListResponse::of)
                .collect(Collectors.toList());
    }
}
