package org.ssafy.d210._common.repository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ssafy.d210._common.entity.RedisTestEntity;

@EnableRedisRepositories
@Repository
public interface RedisTestRepository extends CrudRepository<RedisTestEntity, String> {
}
