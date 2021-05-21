package com.infinitePossibilities;

import com.infinitePossibilities.redis.TestRedis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RedisApplication.class, args);
        TestRedis redis = ctx.getBean(TestRedis.class);
        redis.testRedis();

    }

}
