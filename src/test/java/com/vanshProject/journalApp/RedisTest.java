package com.vanshProject.journalApp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;



    @Disabled
    @Test
    public void checkRedis(){
        redisTemplate.opsForValue().set("key","value");
        Object salary = redisTemplate.opsForValue().get("salary");
        int a = 10;
    }
}
