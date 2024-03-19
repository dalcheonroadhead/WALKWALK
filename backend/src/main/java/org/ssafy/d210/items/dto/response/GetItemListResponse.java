package org.ssafy.d210.items.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.ssafy.d210.items.entity.Items;

@ToString
@Getter
public class GetItemListResponse {
    private Long itemsId;
    private String name;
    private String icon;
    private Integer eggPrice;
    private String effect;
    private String type;

    @Builder
    private GetItemListResponse(Long itemsId, String name, String icon, Integer eggPrice, String effect, String type) {
        this.itemsId = itemsId;
        this.name = name;
        this.icon = icon;
        this.eggPrice = eggPrice;
        this.effect = effect;
        this.type = type;
    }

    public static GetItemListResponse of(Items items) {
        return builder()
                .itemsId(items.getId())
                .name(items.getName())
                .icon(items.getIcon())
                .eggPrice(items.getEggPrice())
                .effect(items.getEffect())
                .type(items.getType().toString())
                .build();
    }
}