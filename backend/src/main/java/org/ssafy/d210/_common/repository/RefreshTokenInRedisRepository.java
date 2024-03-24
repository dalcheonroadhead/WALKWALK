package org.ssafy.d210._common.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210._common.entity.RefreshTokenInRedis;

import java.util.Optional;

@Repository
public interface RefreshTokenInRedisRepository extends CrudRepository<RefreshTokenInRedis, String> {

    // A. [RefreshTokenInRedis Entity]에서 [accessToken]을 [Index]로 설정해놔서 밑의 DB 사용 매서드가 가능하다.
    Optional<RefreshTokenInRedis> findByAccessToken(String accessToken);
}
