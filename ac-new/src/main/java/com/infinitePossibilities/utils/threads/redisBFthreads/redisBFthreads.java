package com.infinitePossibilities.utils.threads.redisBFthreads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import com.alibaba.fastjson.JSONObject;
import com.infinitePossibilities.redis.RedisUtil;


public class redisBFthreads {

	private final static String  key = "key";
	private static final int  Thread_num   = 5;	
	private static DBorRedisQuery dborRedisQuery = new DBorRedisQuery();
	private static final CyclicBarrier cb = new CyclicBarrier(Thread_num);
	
	public static void sendThreads() throws InterruptedException {
		Thread [] threads = new Thread[Thread_num];
		for(int i=0;i<Thread_num;i++){
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						cb.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					dborRedisQuery.dbOrRedisQuery(key);
				}
			});
			threads [i] = thread;
			thread.start();
		}
		for(Thread thread : threads){
			thread.join();
		}
		System.out.println("执行结束======");
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		sendThreads();
	
		
	}
	
	public static int  addStringList() {
		int [] num = new int[] {1,2,3,4,5};
		String [] list  =  new String[num.length];
		  for (int i = 0; i < num.length; i++) {
              JSONObject object = new JSONObject();
              object.put("Id", i + 1); 
              object.put("amount", num[i]); 
              list[i] = object.toJSONString();
              System.out.println(list);
          }
		  RedisUtil.lpush("user", list);

		
		return 0;
		
	}
	
	
	
	
}
