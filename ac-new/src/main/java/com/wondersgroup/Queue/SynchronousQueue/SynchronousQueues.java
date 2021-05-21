package com.wondersgroup.Queue.SynchronousQueue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class SynchronousQueues {

    static SynchronousQueue<String> queue = new SynchronousQueue<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {

                for(int i=0;i<5;i++){

                    long starTime = System.currentTimeMillis();
                    queue.put(i+"   数据推送");
                    long endTime = System.currentTimeMillis();

                    System.out.println(String.format("[%s,%s,take耗时:%s],%s", starTime, endTime, (endTime - starTime),
                            Thread.currentThread().getName()));
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //休眠5秒之后，从队列中take一个元素
        TimeUnit.SECONDS.sleep(5);

        System.out.println(System.currentTimeMillis() + "调用take获取并移除元素," + queue.take());
    }



}
