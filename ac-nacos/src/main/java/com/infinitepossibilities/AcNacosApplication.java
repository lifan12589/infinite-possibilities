package com.infinitepossibilities;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "ac-nacos",autoRefreshed = true)
public class AcNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcNacosApplication.class, args);
    }


}

