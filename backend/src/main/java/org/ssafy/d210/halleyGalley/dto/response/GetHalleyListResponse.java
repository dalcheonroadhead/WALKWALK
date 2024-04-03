package org.ssafy.d210.halleyGalley.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.ssafy.d210.halleyGalley.dto.HalleyDto;
import org.ssafy.d210.members.entity.Members;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetHalleyListResponse {

    private LocalDateTime timeStamp;
    private List<HalleyDto> requestContent;

    @Builder
    private GetHalleyListResponse(LocalDateTime timeStamp, List<HalleyDto> requestContent){
        this.timeStamp = timeStamp;
        this.requestContent = requestContent;
    }

    public static GetHalleyListResponse from(Members member, List<HalleyDto> requestContent){
        return builder()
                .timeStamp(member.getCreatedAt())
                .requestContent(requestContent)
                .build();
    }
}
