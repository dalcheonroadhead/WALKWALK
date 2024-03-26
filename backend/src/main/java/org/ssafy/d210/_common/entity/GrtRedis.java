package org.ssafy.d210._common.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash(value = "grt")
public class GrtRedis {

    @Id
    private Long memberId;

    private String refreshToken;

    @Indexed
    private String accessToken;
}
