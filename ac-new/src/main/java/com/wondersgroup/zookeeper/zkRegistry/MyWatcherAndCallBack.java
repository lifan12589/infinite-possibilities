package com.wondersgroup.zookeeper.zkRegistry;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class MyWatcherAndCallBack implements Watcher,AsyncCallback.StatCallback,AsyncCallback.DataCallback {

    private ZooKeeper zooKeeper;

    private MyConfData myConfData;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void setZooKeeper(ZooKeeper zooKeeper){

        this.zooKeeper = zooKeeper;
    }

    public void setMyConfData(MyConfData myConfData){

        this.myConfData = myConfData;
    }



    //Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {

        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zooKeeper.getData("/conf",this,this,"第二次获取数据");
                break;
            case NodeDeleted:
                myConfData.setData("");
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:
                zooKeeper.getData("/conf",this,this,"第二次获取数据");
                break;
            case NodeChildrenChanged:
                break;
        }

    }


    //StatCallback
    @Override
    public void processResult(int i, String s, Object o, Stat stat) {

        zooKeeper.getData("/conf",this,this,"第一次获取数据");


    }

    //DataCallback
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {

        if(stat!=null){
            System.out.println("取得数据 ： "+new String(bytes));
            myConfData.setData(new String(bytes));

            countDownLatch.countDown();
        }
    }

    public void await() throws InterruptedException {

        zooKeeper.exists("/conf",this,this,"127.0.0.1");

        countDownLatch.await();
    }


}
