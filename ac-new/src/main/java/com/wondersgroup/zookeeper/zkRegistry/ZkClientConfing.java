package com.wondersgroup.zookeeper.zkRegistry;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class ZkClientConfing {

    private ZooKeeper zooKeeper;

    private MyConfData myConfData = new MyConfData();

    private MyWatcherAndCallBack myWatcherAndCallBack = new MyWatcherAndCallBack();



    @Before
    public void conn() throws Exception {

        zooKeeper = ZkClientUtil.getZkClient();
    }


    @After
    public void close() throws InterruptedException {

        zooKeeper.close();
    }


//    @Test
    @PostConstruct
    public void getConfData() throws InterruptedException {

        try {
            zooKeeper = ZkClientUtil.getZkClient();
        } catch (Exception e) {
            e.printStackTrace();
        }

        myWatcherAndCallBack.setZooKeeper(zooKeeper);

        myWatcherAndCallBack.setMyConfData(myConfData);

        myWatcherAndCallBack.await();



        while (true){

            if(myConfData.getData().equals("")){
                System.out.println("配置中心数据为空！！！！！");
                myWatcherAndCallBack.await();//等待数据输入
            }

            System.out.println("新数据 ： "+myConfData.getData());

            Thread.sleep(30000);
        }

    }



}
