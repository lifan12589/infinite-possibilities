package com.wondersgroup.utils.threads.ThreadLocal;

/**
 * ThreadLocal
 * https://ifeve.com/java-threadlocal%E7%9A%84%E4%BD%BF%E7%94%A8/
 * @author lifan
 */
public class Thread_Local implements Runnable {

	//创建一个ThreadLocal变量
	private ThreadLocal threadLocal = new ThreadLocal();
	
	@Override
	public void run() {

		//threadLocal放入随机int值
		threadLocal.set((int)(Math.random()*100));
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		System.out.println(Thread.currentThread().getName()+"     "+threadLocal.get());

//		getData();

		//防止内存泄漏必须手动调用 remove() 方法
		threadLocal.remove();
		System.out.println(Thread.currentThread().getName()+" remove后："+threadLocal.get());
		
	}


	public void getData(){

		//threadLocal取值
		System.out.println("getData()  "+Thread.currentThread().getName()+"     "+threadLocal.get());

	}
	
	public static void main(String[] args) {
		
		Thread_Local thread_Local = new Thread_Local();
		Thread thread1 = new Thread(thread_Local);
		Thread thread2 = new Thread(thread_Local);
		thread1.start();
		thread2.start();
		
	}







	/**
	 * InheritableThreadLocal  
	 * 用以获取父线程的 ThreadLocal 值
	 * https://www.jb51.net/article/126122.htm
	 * @author lifan
	 */
	static class Inheritable_ThreadLocal implements Runnable{
		
		private static InheritableThreadLocal<String> inLocal000 = new InheritableThreadLocal<>();
		private static InheritableThreadLocal<String> inLocal111 = new InheritableThreadLocal<>();

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName()+"    value : "+inLocal000.get());
			System.out.println(Thread.currentThread().getName()+"    value : "+inLocal111.get());
			
		}


		public static void main(String[] args) {

			Inheritable_ThreadLocal in_Local = new Inheritable_ThreadLocal();
			inLocal000.set("InheritableThreadLocal000");
			inLocal111.set("InheritableThreadLocal111");
			System.out.println("inLocal000 : "+inLocal000.get());
			System.out.println("inLocal111 : "+inLocal111.get());
			Thread thread1 = new Thread(in_Local);
			Thread thread2 = new Thread(in_Local);
			thread1.start();
			thread2.start();
		}
		
	}
	
}
