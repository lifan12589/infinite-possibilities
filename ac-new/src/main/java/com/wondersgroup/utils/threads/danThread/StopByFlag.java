package com.wondersgroup.utils.threads.danThread;


public class StopByFlag implements Runnable{

    volatile boolean stop;

    void tellToStop() {
        stop = true;
    }

    @Override
    public void run() {
        System.out.println("进入不可停止区域 1。。。");
        try {
            Thread.sleep(2000);
            System.out.println("退出不可停止区域 1。。。");
            System.out.println(String.format("检测标志stop = %s",stop));
            if (stop) {
                System.out.println("停止执行");
                return;
            }
            System.out.println("进入不可停止区域 2。。。");
            Thread.sleep(2000);
            System.out.println("退出不可停止区域 2。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
      }
    }

    /**
     * 停止：传达停止的信号，处理完事情就自主停止。
     *      线程在预设的地点检测flag，来决定是否停止。
     * @param args
     */
    public static void main(String[] args) {

        StopByFlag stopByFlag = new StopByFlag();
        new Thread(stopByFlag).start();
        stopByFlag.tellToStop();
    }




}
