package com.wondersgroup;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * https://www.codingapi.com/      官网总入口
 * https://www.codingapi.com/docs/txlcn-start/    中文文档
 * https://github.com/codingapi/tx-lcn      github
 *
 * 启动顺序：  eureka  tm  pro  con
 *
 */
@SpringBootApplication
@EnableTransactionManagerServer
public class LcnTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnTmApplication.class, args);
    }

}
