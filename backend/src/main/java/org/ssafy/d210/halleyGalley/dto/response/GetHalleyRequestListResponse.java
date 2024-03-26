package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.halleyGalley.dto.HalleyDto;

import java.util.List;

@Getter
public class GetHalleyRequestListResponse {
    private List<HalleyDto> requestContent;

    @Builder
    private GetHalleyRequestListResponse(List<HalleyDto> requestContent){
        this.requestContent = requestContent;
    }

    public static GetHalleyRequestListResponse of(List<HalleyDto> requestContent){
        return builder()
                .requestContent(requestContent)
                .build();
    }
}
