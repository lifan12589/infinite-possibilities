package com.infinitepossibilities.Text;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ZcTest {

    @NacosInjected
    private NamingService namingService;

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    public void InData() throws NacosException {

        System.out.println("qu zhu ce ...");
        namingService.registerInstance(applicationName,"127.0.0.1",port);

    }


}
