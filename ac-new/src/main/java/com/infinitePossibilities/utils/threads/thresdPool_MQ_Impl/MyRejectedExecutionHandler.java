package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl;

        import java.util.Random;
        import java.util.concurrent.RejectedExecutionHandler;
        import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义线程池拒绝策略
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

//        int i = 1/0;
        //拒绝掉的任务 开启新线程去执行
        new Thread(r,"新启线程"+new Random().nextInt(10)).start();


    }
}
