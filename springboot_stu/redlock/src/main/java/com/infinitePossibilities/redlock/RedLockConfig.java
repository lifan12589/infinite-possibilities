package com.infinitePossibilities.redlock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
public class RedLockConfig {

    //红锁:
    @Bean(name = "redissonRed1")
    @Primary
    public RedissonClient redissonRed1(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed2")
    public RedissonClient redissonRed2(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6380").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed3")
    public RedissonClient redissonRed3(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6381").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed4")
    public RedissonClient redissonRed4(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6382").setDatabase(0);
        return Redisson.create(config);
    }
    @Bean(name = "redissonRed5")
    public RedissonClient redissonRed5(){
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6383").setDatabase(0);
        return Redisson.create(config);
    }

}
