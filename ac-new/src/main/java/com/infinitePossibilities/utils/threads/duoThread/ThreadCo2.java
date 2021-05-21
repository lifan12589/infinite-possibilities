package com.infinitePossibilities.utils.threads.duoThread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class ThreadCo2 {

    static final int COUNT = 5;

    //当给定数量的线程（线程）等待时，它将跳闸，当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
    static CyclicBarrier cb = new CyclicBarrier(COUNT, new Singer());

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < COUNT; i++) {
            new Thread(new Staff(i, cb)).start();
        }
        synchronized (ThreadCo2.class) {
            ThreadCo2.class.wait();
        }
    }


    static class Singer implements Runnable {

        @Override
        public void run() {
            //最后一个进入屏障的线程执行。
            System.out.println(Thread.currentThread().getName()+"最后执行完的线程.....");
        }
    }

    static class Staff implements Runnable {

        CyclicBarrier cb;
        int num;

        Staff(int num, CyclicBarrier cb) {
            this.num = num;
            this.cb = cb;
        }

        @Override
        public void run() {
            System.out.println("线程(%d)开始执行业务1。。。"+ num);
            doingLongTime();
            System.out.println("线程(%d)完成业务1。。。"+ num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程(%d)开始执行业务2。。。"+ num);
            doingLongTime();
            System.out.println("线程(%d)完成业务2。。。"+ num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程(%d)开始执行业务3。。。"+ num);
            doingLongTime();
            System.out.println("线程(%d)完成业务3。。。"+ num);
            try {
                cb.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("线程(%d)结束。。。"+ num);
        }

    }



    public static int random(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }

    public static void doingLongTime() {
        try {
            int second = random(5, 10);
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}
