package com.infinitepossibilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }


    /*
    访问：
        http://localhost:18080/user-consumer/user?ids=123,456,789

        http://localhost:18080/user-consumer/user/hystrix?hids=123,456,789

        http://localhost:18080/user-consumer/user/feign?hids=123,456,789
     */
}
