package com.wondersgroup.utils.threads.danThread;

public class StartByJoin implements Runnable {

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName()+" 进入不可暂停区域 1。。。");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+" 退出不可暂停区域 1。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 插队:插队到你前面，必须等他完事后才轮到你
     *     join方法可以让某个线程插到自己前面，等它执行完，自己才会继续执行。
     * @param args
     */
    public static void main(String[] args) {

        StartByJoin startByJoin = new StartByJoin();
        Thread thread = new Thread(startByJoin);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取执行...");

    }

}
