package com.infinitePossibilities.utils.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TempThread implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"被执行！");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

     public static void main(String[] args) {
         // 创建数组型缓冲等待队列  阻塞队列
         BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);
         // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
         ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 6, 50, TimeUnit.MILLISECONDS, bq);
 
         // 创建3个任务
         Runnable t1 = new TempThread();
         Runnable t2 = new TempThread();
         Runnable t3 = new TempThread();
         // Runnable t4 = new TempThread();
         // Runnable t5 = new TempThread();
         // Runnable t6 = new TempThread();
 
         // 3个任务在分别在3个线程上执行
         tpe.execute(t1);
         tpe.execute(t2);
         tpe.execute(t3);
         // tpe.execute(t4);
         // tpe.execute(t5);
         // tpe.execute(t6);
 
         // 关闭自定义线程池
         tpe.shutdown();
     }
 }





