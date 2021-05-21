package com.wondersgroup.utils.threads;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.LinkedBlockingQueue;  
import java.util.concurrent.RejectedExecutionHandler;  
import java.util.concurrent.ThreadPoolExecutor;  
import java.util.concurrent.TimeUnit;  
  
public class ThreadPoolDemo {  
  
    public static void main(String args[]) {  
        // 创建任务对象  
        MyTask task = new MyTask();  
        // 获取自定义线程池  
        ExecutorService es = getMyThreadPool();  
        for(int i=0; i<20; i++) {  
            // 向线程池提交任务  
            es.submit(task);  
        }  
    }  
      
    // 自定义线程池，我们创建一个线程数固定的线程池  
    public static ExecutorService getMyThreadPool() {  
        ExecutorService es = new ThreadPoolExecutor(  
            // 设置线程池大小  
            5, 5, 0L, TimeUnit.MILLISECONDS,   
            // 设置缓存队列  
            new LinkedBlockingQueue<Runnable>(5),  
            // 设置线程工厂  
            Executors.defaultThreadFactory(),  
            // 设置拒绝策略  
            new RejectedExecutionHandler() {  
                @Override  
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {  
                    System.out.println(r.toString() + " is discard! ");    // 输出日志后直接丢弃任务  
                }  
            });  
        return es;  
    }  
      
    public static class MyTask implements Runnable {  
        @Override  
        public void run() {  
            System.out.println(System.currentTimeMillis() + ": Thread ID: " + Thread.currentThread().getId()+"     Thread Name："+Thread.currentThread().getName());    
            try {  
                Thread.sleep(1000);  
            }catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }     
}  