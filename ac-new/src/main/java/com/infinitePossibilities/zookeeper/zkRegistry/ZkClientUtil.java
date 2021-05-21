package com.infinitePossibilities.zookeeper.zkRegistry;

import org.apache.zookeeper.ZooKeeper;
import java.util.concurrent.CountDownLatch;

public class ZkClientUtil {

    private static ZooKeeper zooKeeper;

    private static DefaultWatcher defaultWatcher = new DefaultWatcher();

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static String address = "localhost:2181,localhost:2182,localhost:2183,localhost:2184/zkRegistry";


    public static ZooKeeper getZkClient() throws Exception {


        zooKeeper = new ZooKeeper(address,1000,defaultWatcher);

        defaultWatcher.setCountDownLatch(countDownLatch);

        countDownLatch.await();

        return zooKeeper;

    }


}
