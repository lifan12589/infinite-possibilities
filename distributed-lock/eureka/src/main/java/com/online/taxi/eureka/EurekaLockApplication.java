package com.online.taxi.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaLockApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaLockApplication.class, args);
	}

}
