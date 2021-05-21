package com.wondersgroup.utils.threads.danThread;

import sun.security.krb5.internal.TGSRep;

public class StopByInterrupt implements Runnable {

    @Override
    public void run() {
        System.out.println("进入暂停。。。");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("收到中断异常。。。");
            System.out.println("做一些相关处理。。。");
        }
        System.out.println("继续执行或选择退出。。。");
    }

    /**
     *叫醒: 线程在sleep或wait时，是处于无法交互的状态的，
     *     此时只能使用interrupt方法中断它，线程会被激活并收到中断异常。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        StopByInterrupt stopByInterrupt = new StopByInterrupt();
        Thread thread = new Thread(stopByInterrupt);
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();


    }
}
