//package com.wondersgroup.zookeeper.zkLock;
//
//import org.apache.zookeeper.*;
//import org.apache.zookeeper.data.Stat;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//
//import java.io.IOException;
//import java.util.concurrent.BrokenBarrierException;
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//
//public class Main {
//
//    private static final int  Thread_num   = 5;
//    private static final CyclicBarrier cb = new CyclicBarrier(Thread_num);
//
//    public static void main(String[] args) throws Exception {
//
//        Thread [] threads = new Thread[Thread_num];
//        for(int i=0;i<Thread_num;i++){
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        cb.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (BrokenBarrierException e) {
//                        e.printStackTrace();
//                    }
//
//                    Lock lock = new ZookeeperLock("localhost:2181",5000,"/zkLock","lockKey");
//                    try {
//                        if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
//                            System.out.println(Thread.currentThread().getName() + "   获得锁执行业务");
//                            //减数据库
//                            int count = 1;
//                            String st_fj_id = "keys";
//                            String updateSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
//                            Object[] updateObject = new Object[] {count,st_fj_id};
//                            RecordSet updateRs  = SQL.execute(updateSql,updateObject);
//                            int number = updateRs.TOTAL_RECORD_COUNT;
//                            //影响行数
//                            System.out.println("数据库影响行数:"+number);
//
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }finally {
//                        lock.unlock();
//
//                    }
//
//                }
//            });
//            threads [i] = thread;
//            thread.start();
//        }
//        for(Thread thread : threads){
//            thread.join();
//        }
//        System.out.println("执行结束======");
//
//    }
//
//
//
//
//    private static void createRoot() throws IOException {
//         ZooKeeper zooKeeper = new ZooKeeper("localhost:2181", 5000, new Watcher() {
//             @Override
//             public void process(WatchedEvent event) {
//                 System.out.println("事件类型为:" + event.getType());
//                 System.out.println("事件发生的路径:" + event.getPath());
//                 System.out.println("通知状态为:" +event.getState());
//
//             }
//         });
//        try {
//            Stat s = zooKeeper.exists("/zkLock", false);
//            System.out.println(s);
//            if (s == null) {
//                zooKeeper.create("/zkLock", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            } else {
//                System.out.println("创建createRoot，已存在");
//            }
//        } catch (Exception se) {
//            System.out.println("创建createRoot，发生 Exception");
//            se.printStackTrace();
//        }
//    }
//
//
//
//
//    }
