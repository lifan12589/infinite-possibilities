package com.infinitePossibilities.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


@Configuration
public class MyTemplate {

    @Bean
    public StringRedisTemplate myFactory(RedisConnectionFactory fc) {

        StringRedisTemplate tp = new StringRedisTemplate(fc);

        tp.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        return tp;
    }


}
