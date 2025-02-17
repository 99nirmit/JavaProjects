package com.beans.cruddb.service;

import com.beans.cruddb.domain.RedisUser;
import com.beans.cruddb.repository.RedisUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisUserService {

    private final RedisUserRepository redisUserRepository;

    @Autowired
    public RedisUserService(RedisUserRepository redisUserRepository) {
        this.redisUserRepository = redisUserRepository;
    }

    public void saveUser(RedisUser user){
        redisUserRepository.save(user);
    }

    public Optional<RedisUser> getByUserId(String id){
        return redisUserRepository.findById(id);
    }

    public void deleteById(String id){
        redisUserRepository.deleteById(id);
    }

    public List<RedisUser> getAllUsers(){
        return (List<RedisUser>) redisUserRepository.findAll();
    }
}
