package com.wondersgroup.utils;

import com.wondersgroup.zookeeper.zkRegistry.ZkClientConfing;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class TomcatStart implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("TomcatStart.java  ....");

    }


}
