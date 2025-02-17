package com.beans.cruddb.api;

import com.beans.cruddb.domain.RedisUser;
import com.beans.cruddb.service.RedisUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/redis/api/test")
public class RedisTestApi {

    private final RedisUserService redisUserService;


    @Autowired
    public RedisTestApi(RedisUserService redisUserService) {
        this.redisUserService = redisUserService;
    }


    @PostMapping("/save")
    public String testRedis(@RequestBody RedisUser redisUser){
        redisUserService.saveUser(redisUser);
        return "Redis is working.";
    }
}
