package com.wondersgroup.utils.threads;

public class Threads implements Runnable {
	
	private int tickets=50;
    public void run() {
        while(true) {                                           //通过死循环语句打印语句
            if(tickets>0) {       
                String name=Thread.currentThread().getName();   //获取当前线程的名称
                //while循环依次对ticket属性进行自减操作 直到ticket属性值为0时，跳出循环
                System.out.println(name+"-->"+  tickets-- );  
            }else {
                break;
            }
        }
    }
	

	
	public static void main(String[] args) {
//		创建线程任务
		Threads ticket02 = new Threads();
         //定义4个线程对象，代表4个窗口
        Thread t1 = new Thread(ticket02,"窗口1");
        Thread t2 = new Thread(ticket02,"窗口2");
        Thread t3 = new Thread(ticket02,"窗口3");
        Thread t4 = new Thread(ticket02,"窗口4");

        //开启四个线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();
//		 double charIndex = Math.floor(Math.random()*36);  
//		 System.out.println(charIndex);
//        
	}
}
