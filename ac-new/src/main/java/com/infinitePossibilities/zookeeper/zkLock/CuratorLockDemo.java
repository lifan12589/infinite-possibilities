package com.infinitePossibilities.zookeeper.zkLock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 简单理解
 */
public class CuratorLockDemo {

    public static void main (String[] args) {
        String servers = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .retryPolicy(new ExponentialBackoffRetry(10000, 3))
                .connectString(servers).build();
        curator.start();
        final InterProcessMutex lock = new InterProcessMutex(curator, "/global_lock");

        Executor pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i ++) {
            pool.execute(new Runnable() {
                public void run() {
                    try {
                        lock.acquire();

                        int count = 1;
                        String st_fj_id = "keys";
                        System.out.println(Thread.currentThread().getId()+" ===");
                        String insertSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
                        Object[] insertObject = new Object[] {count,st_fj_id};
                        RecordSet rs  = SQL.execute(insertSql,insertObject);
                        int number = rs.TOTAL_RECORD_COUNT;
                        //影响行数
                        System.out.println(Thread.currentThread().getId()+"   数据库影响行数  "+number );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally{
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
