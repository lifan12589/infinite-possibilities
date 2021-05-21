package com.wondersgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//引入SpringBoot功能
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ClientApplication {
    public static void main(String[] args) {
    	//启动Springboot容器,即启动orderService服务
        SpringApplication.run(ClientApplication.class, args);
    }
}