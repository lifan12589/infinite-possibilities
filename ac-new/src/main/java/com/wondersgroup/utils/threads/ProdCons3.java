package com.wondersgroup.utils.threads;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProdCons3 {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);//阻塞队列 容量为10

        Thread cus=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(blockingQueue.isEmpty()){
                        System.out.println("队列为空");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {
                        Integer take = blockingQueue.take();//拿走阻塞队列的数据
                        Thread.sleep(1000);
//                        System.out.println("消费者 拿走了"+take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        },"消费者");
        Thread pro=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(blockingQueue.size()>0){
                        System.out.println("队列有值："+blockingQueue.size());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    Random random = new Random();
                    int tmp=random.nextInt(10);
                    blockingQueue.offer(tmp);//提供数据
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("生产者生产了 +"+tmp);
                }

            }
        },"生产者");
        pro.start();
        cus.start();

    }

}
