package org.ssafy.d210.members.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class LastLoginInfo {

    LocalDateTime updatedAt;

    @Builder
    private LastLoginInfo(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    public static LastLoginInfo of(LocalDateTime updatedAt){
        return builder()
                .updatedAt(updatedAt)
                .build();
    }

}
