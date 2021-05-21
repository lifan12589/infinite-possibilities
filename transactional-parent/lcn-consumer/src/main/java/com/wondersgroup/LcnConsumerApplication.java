package com.wondersgroup;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
public class LcnConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnConsumerApplication.class, args);
    }

}
