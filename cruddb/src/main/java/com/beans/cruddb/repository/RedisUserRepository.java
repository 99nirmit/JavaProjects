package com.beans.cruddb.repository;

import com.beans.cruddb.domain.RedisUser;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableRedisRepositories
public interface RedisUserRepository extends CrudRepository<RedisUser, String> {
}
