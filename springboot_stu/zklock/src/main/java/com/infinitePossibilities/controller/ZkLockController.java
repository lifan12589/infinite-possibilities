package com.infinitePossibilities.controller;


//import com.infinitePossibilities.aspect.HttpAspect;
import com.infinitePossibilities.config.ZKUtils;
import com.infinitePossibilities.watch.WatchCallBack;
import org.apache.zookeeper.ZooKeeper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/zk")
public class ZkLockController {

//    ZooKeeper zk = HttpAspect.getZk();
      ZooKeeper zk = ZKUtils.getZK();

//    @Before
//    public void conn (){
//        zk  = ZKUtils.getZK();
//        System.out.println("zk= "+zk);
//    }
//    @After
//    public void close (){
//        try {
//            zk.close();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }


    @RequestMapping("/lock")
    public String Lock(){

//        for(int i=0;i<10;i++){

           new Thread(){

               @Override
               public void run() {

                   WatchCallBack watchCallBack = new WatchCallBack();
//                   System.out.println("zk--> "+zk);
                   watchCallBack.setZk(zk);
                   String threadName = Thread.currentThread().getName();
                   watchCallBack.setThreadName(threadName);

                   //加锁
                   watchCallBack.tryLock();

                   try {
                       Thread.sleep(2000);
                       System.out.println("执行业务代码....");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   //解锁
                   watchCallBack.unLock();
               }
           }.start();


//        }
        return "true";
    }
}