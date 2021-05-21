package com.infinitePossibilities.config;

import org.apache.zookeeper.ZooKeeper;
import java.util.concurrent.CountDownLatch;

public class ZKUtils {

    private  static ZooKeeper zk;

    private static String address = "localhost:2181,localhost:2182,localhost:2183,localhost:2184/zkLocks";

    private static DefaultWatch watch = new DefaultWatch();

    private static CountDownLatch init  =  new CountDownLatch(1);
    public static ZooKeeper  getZK(){

        try {
            zk = new ZooKeeper(address,1000,watch);
            watch.setCc(init);
            init.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return zk;
    }


}
