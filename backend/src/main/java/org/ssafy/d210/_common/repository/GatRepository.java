package org.ssafy.d210._common.repository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210._common.entity.GatRedis;

import java.util.Optional;

@EnableRedisRepositories
@Repository
public interface GatRepository extends CrudRepository<GatRedis, Long> {

    Optional<GatRedis> findByMemberId(Long id);
}
