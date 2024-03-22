package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PutGalleyResponseResponse {
    private Boolean isHalleyGalley;

    @Builder
    private PutGalleyResponseResponse(Boolean isHalleyGalley){
        this.isHalleyGalley = isHalleyGalley;
    }

    public static PutGalleyResponseResponse of(Boolean isAccept){
        return builder()
                .isHalleyGalley(isAccept)
                .build();
    }
}
