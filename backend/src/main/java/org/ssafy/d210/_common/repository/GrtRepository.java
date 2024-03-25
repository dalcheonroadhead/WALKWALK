package org.ssafy.d210._common.repository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210._common.entity.GrtRedis;

import java.util.Optional;

@EnableRedisRepositories
@Repository
public interface GrtRepository extends CrudRepository<GrtRedis, Long> {

    Optional<GrtRedis> findByAccessToken(String accessToken);
    Optional<GrtRedis> findByMemberId(Long id);
}
