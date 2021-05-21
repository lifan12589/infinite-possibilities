package com.infinitePossibilities.utils.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdCons {

    public static void main(String[] args) {
        Queue queue = new Queue();
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
//        new Thread(new Consumer(queue)).start();//再起一个消费者
    }

    static class Producer implements Runnable {

        Queue queue;

        Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i < 10000; i++) {
                    Thread.sleep(1000);
                    queue.putEle(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class Consumer implements Runnable {

        Queue queue;

        Consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 1; i < 10000; i++) {
                    Thread.sleep(2000);
                    queue.takeEle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    static class Queue {
        Lock lock = new ReentrantLock();
        Condition prodCond  = lock.newCondition();
        Condition consCond = lock.newCondition();

        final int CAPACITY = 3;
        Object[] container = new Object[CAPACITY];
        int count = 0;
        int putIndex = 0;
        int takeIndex = 0;

        public void putEle(Object ele) throws InterruptedException {
            try {
                lock.lock();
                while (count == CAPACITY) {
                    System.out.println(String.format("队列已满：%d，生产者开始 Seelp。。。", count));
                    prodCond.await();
                }
                container[putIndex] = ele;
                System.out.println(String.format("生产元素：%d", ele));
                putIndex++;
                if (putIndex >= CAPACITY) {
                    putIndex = 0;
                }
                count++;
                System.out.println(String.format("队列已有 :"+String.valueOf(count)));
                consCond.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public Object takeEle() throws InterruptedException {
            try {
                lock.lock();
                while (count == 0) {
                    System.out.println(String.format("队列已空：%d，消费者开始 Seelp。。。", count));
                    consCond.await();
                }
                Object ele = container[takeIndex];
                System.out.println(String.format("消费元素：%d", ele));
                takeIndex++;
                if (takeIndex >= CAPACITY) {
                    takeIndex = 0;
                }
                count--;
                System.out.println(String.format("队列剩余 :" +String.valueOf(count)));
                prodCond.signalAll();
                return ele;
            } finally {
                lock.unlock();
            }
        }
    }


}
