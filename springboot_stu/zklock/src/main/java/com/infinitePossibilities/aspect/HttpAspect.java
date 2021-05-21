//package com.infinitePossibilities.aspect;
//
//import com.infinitePossibilities.config.ZKUtils;
//import org.apache.zookeeper.ZooKeeper;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//
//@Aspect
//@Component
//public class HttpAspect {
//
//
//    public static ZooKeeper zk;
//    static CountDownLatch cd = new CountDownLatch(1);
//
//    public static ZooKeeper getZk() {
//        try {
//            cd.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return zk;
//    }
//
//
//    @Pointcut("execution(public * com.infinitePossibilities.controller.ZkLockController.*(..))")
//    public void zk(){
//
//    }
//
//    @Before("zk()")
//    public void conn(){
//
//        zk = ZKUtils.getZK();
//        cd.countDown();
//        System.out.println("zk  开启....."+zk);
//    }
//
//    @After("zk()")
//    public void close(){
//
//        try {
//            zk.close();
//            System.out.println("zk  关闭.....");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//
//
//
//
//}
