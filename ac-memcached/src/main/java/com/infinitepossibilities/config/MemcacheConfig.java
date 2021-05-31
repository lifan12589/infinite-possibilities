package com.infinitepossibilities.config;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.net.InetSocketAddress;

@Configuration
public class MemcacheConfig {

    @Value("${memcache.ip}")
    private String ip;

    @Value("${memcache.port}")
    private int port;

    @Bean
    public MemcachedClient getMemcachedClient(){

        MemcachedClient memcachedClient = null;

        try {
            memcachedClient = new MemcachedClient(new InetSocketAddress(ip,port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memcachedClient;

    }


}
