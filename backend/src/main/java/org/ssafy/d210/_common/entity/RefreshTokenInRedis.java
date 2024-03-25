package org.ssafy.d210._common.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@AllArgsConstructor
@Getter
// 기한 설정 3일
@RedisHash(value = "jwtToken", timeToLive = 60*60*24*3)
public class RefreshTokenInRedis {
    @Id
    private String id;

    private String refreshToken;

    // Spring data Redis 모듈의 주요 어노테이션 중 하나.
    // [Redis]의 보조 인덱스 생성에 사용, @Id가 붙여진 객체 외에도 [@Indexed]가 붙여진 객체로도 값을 조회하는 것이 가능해짐
    @Indexed
    private String accessToken;
}
