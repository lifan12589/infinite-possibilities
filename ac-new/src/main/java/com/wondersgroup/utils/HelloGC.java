package com.wondersgroup.utils;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class HelloGC {

    @PostConstruct
    public void initService() throws InterruptedException {

        System.out.println("HelloGC!");
//        List list = new LinkedList();
//        for(;;) {
//            Thread.sleep(50);
//            byte[] b = new byte[1024*1024];
//            list.add(b);
//        }

    }




}
