package com.infinitePossibilities.utils.threads.duoThread;

import java.util.concurrent.CountDownLatch;

public class ThreadCo1 {

    static final int COUNT = 20;

    static CountDownLatch cdl = new CountDownLatch(COUNT);


    /**
     *  20个线程分别在计算数据，等它们都结束后得到20个中间结果，最后这1个线程再进行后续汇总、处理等。
     *  每完成一个线程，计数器减1，当减到0时，被阻塞的线程自动执行。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Thread(new ZhuThread(cdl)).start();
        Thread.sleep(1000);
        for (int i = 0; i < COUNT; i++) {
            new Thread(new ZiThread(i, cdl)).start();
        }
        synchronized (ThreadCo1.class) {
            ThreadCo1.class.wait();
        }
    }


    static class ZhuThread implements Runnable {

        CountDownLatch cdl;

        ZhuThread(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {
            System.out.println("主线程开始执行。。。");
            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主线程执行结束。。。");
        }

    }

    static class ZiThread implements Runnable {

        CountDownLatch cdl;
        int num;

        ZiThread(int num, CountDownLatch cdl) {
            this.num = num;
            this.cdl = cdl;
        }

        @Override
        public void run() {
            System.out.println(String.format("子线程(%d)开始执行。。。", num));
//            doingLongTime();
            System.out.println(String.format("子线程(%d)执行结束。。。", num));
            cdl.countDown();
        }

    }


//    public static int random(int min, int max) {
//        return min + new Random().nextInt(max - min + 1);
//    }
//
//    public static void doingLongTime() {
//        try {
//            int second = random(5, 10);
//            Thread.sleep(second);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }




}
