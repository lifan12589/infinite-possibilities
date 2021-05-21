package com.infinitePossibilities.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class TestRedis {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("myFactory")
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ObjectMapper objectMapper;


    public void testRedis() {


//        stringRedisTemplate.opsForValue().set("hello01","china");
//        System.out.println(stringRedisTemplate.opsForValue().get("hello01"));


//        RedisConnection conn = redisTemplate.getConnectionFactory().getConnection();
//        conn.set("hello02".getBytes(), "ceshi".getBytes());
//        System.out.println(new String(conn.get("hello02".getBytes())));


//        HashOperations<String, Object, Object> hash = stringRedisTemplate.opsForHash();
//        hash.put("sean","name","zhouzhilei");
//        hash.put("sean","age","22");
//        System.out.println(hash.entries("sean"));


        Person p = new Person();
        p.setName("测试人员");
        p.setAge(16);

          //Jackson2JsonRedisSerializer序列化  （下面已改成自定义： MyTemplate-->  @Qualifier("myFactory")）
//        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));

        Jackson2HashMapper jm = new Jackson2HashMapper(objectMapper, false);

        stringRedisTemplate.opsForHash().putAll("LF", jm.toHash(p));

        Map map = stringRedisTemplate.opsForHash().entries("LF");

        Person per = objectMapper.convertValue(map, Person.class);
        System.out.println(per.getName());



        //发布订阅   PUBLISH channel message     SUBSCRIBE channel  UNSUBSCRIBE channel

        stringRedisTemplate.convertAndSend("myChannel", "myMessage");

        RedisConnection cc = stringRedisTemplate.getConnectionFactory().getConnection();
        cc.subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                byte[] body = message.getBody();
                System.out.println(new String(body));
            }
        }, "myChannel".getBytes());

        while (true) {

            for(int i=1;i<10000;i++){
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stringRedisTemplate.convertAndSend("myChannel", "第 "+i+" 次发送...");
            }


        }


    }


}
