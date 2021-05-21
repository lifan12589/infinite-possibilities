/**
 * 
 */
/**
 * @author lifan
 *
 */
package com.wondersgroup.utils.threads;

public class ThreadTest {

    /**
     * 定义一个测试
     */
    private static volatile boolean flag = false;
    
    
    public static void main(String[] args) throws Exception {
    	 notifyThreadWithVolatile();
	}
    
    
    /**
     * 计算I++，当I==5时，通知线程B
     * @throws Exception
     */
    private static void notifyThreadWithVolatile() throws Exception {
        Thread thc = new Thread("线程A"){
            @Override
            public void run() {
            	 System.out.println("1");
                for (int i = 0; i < 10; i++) {
                    if (i == 5) {
                        flag = true;
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "====" + i);
                }
            }
        };

        Thread thd = new Thread("线程B") {
            @Override
            public void run() {
            	 System.out.println("2");
                while (true) {
                    // 防止伪唤醒 所以使用了while
                    while (flag) {
                        System.out.println(Thread.currentThread().getName() + "收到通知");
                        System.out.println("do something");
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return ;
                    }
                }
            }
        };

        thd.start();
        Thread.sleep(1000L);
        thc.start();

    }
}