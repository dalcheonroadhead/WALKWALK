package org.ssafy.d210._common.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
@Builder
@Getter

@AllArgsConstructor

public class RedisTestEntity {
    @Id
    public String idx;

    public String userId;
    public String content;
}
