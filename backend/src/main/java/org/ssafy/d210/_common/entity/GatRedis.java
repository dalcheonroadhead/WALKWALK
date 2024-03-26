package org.ssafy.d210._common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor
@Getter
@RedisHash(value = "gat" ,timeToLive = 60*60)
public class GatRedis {

    @Id
    private Long memberId;
    private String accessToken;

}
