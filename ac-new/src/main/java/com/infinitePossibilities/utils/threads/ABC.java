package com.infinitePossibilities.utils.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ABC {
    static int count=0;//利用count来计数 和3取余 0=A 1=B 2=C
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();//可重入锁
        Condition Acon = lock.newCondition();//等待/通知A
        Condition Bcon = lock.newCondition();//等待/通知B
        Condition Ccon = lock.newCondition();//等待/通知C


        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){//如果要改成 输出10次 将这里的while改为for 10次就行
                    lock.lock();
                    try {
                        while(count%3!=0)
                            Acon.await();//当前取余值不为0 等待唤醒
                        System.out.println(Thread.currentThread().getName() + ":  A");
                        count++;
                        Bcon.signal();//唤醒B 该B执行了打印了
                    } catch (Exception e) {
                    } finally {
                        lock.unlock();
                    }
                }


            }
        }, "1");
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        while(count%3!=1)
                            Bcon.await();
                        System.out.println(Thread.currentThread().getName() + ":  B");
                        count++;
                        Ccon.signal();
                    } catch (Exception e) {
                    } finally {
                        lock.unlock();
                    }
                }


            }
        }, "2");
        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    try {
                        while(count%3!=2)
                            Ccon.await();
                        System.out.println(Thread.currentThread().getName() + ":  C");
                        count++;
                        Acon.signal();
                    } catch (Exception e) {
                    } finally {
                        lock.unlock();
                    }
                }


            }
        }, "3");
        A.start();
        B.start();
        C.start();
    }
}