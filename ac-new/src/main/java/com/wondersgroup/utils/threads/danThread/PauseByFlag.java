package com.wondersgroup.utils.threads.danThread;

public class PauseByFlag implements Runnable {
    volatile boolean pause;

    void tellToPause() {
        pause = true;
    }

    void tellToResume() {
        synchronized (this) {
            //notify()唤醒沉睡的线程
            this.notify();
        }
    }

    @Override
    public void run() {

        try {
            System.out.println("进入不可暂停区域 1。。。");
            Thread.sleep(2000);
            System.out.println("退出不可暂停区域 1。。。");
            System.out.println(String.format("检测标志pause = %s", String.valueOf(pause)));
            if (pause) {
                System.out.println("暂停执行");
                try {
                    synchronized (this) {
                        //wait()使当前线程阻塞
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("恢复执行");
            }
            System.out.println("进入不可暂停区域 2。。。");
            Thread.sleep(2000);
            System.out.println("退出不可暂停区域 2。。。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     *暂停/恢复： 传达暂停的信号，处理完手头的事情就自己主动暂停了。但是恢复是无法自主进行的，只能由操作系统来恢复线程的执行(注意时间)。
     *          还是在预设的地点检测flag。然后就是wait/notify配合使用。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        PauseByFlag pauseByFlag = new PauseByFlag();
        new Thread(pauseByFlag).start();
        pauseByFlag.tellToPause();
        Thread.sleep(8000);
        pauseByFlag.tellToResume();

    }
}
