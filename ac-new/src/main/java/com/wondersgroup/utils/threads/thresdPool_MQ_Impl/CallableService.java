//package com.wondersgroup.utils.threads.thresdPool_MQ_Impl;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.stereotype.Service;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//
//import java.sql.Timestamp;
//import java.util.concurrent.*;
//
//@Service
//public class CallableService {
//
//	private ExecutorService executorService = Executors.newFixedThreadPool(3);
//
//	//自定义线程池
//	private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//													2,4,
//													60,TimeUnit.SECONDS,
//													new ArrayBlockingQueue<>(4),
//													Executors.defaultThreadFactory(),
//													//自定义拒绝策略
//													new MyRejectedExecutionHandler()
//													);
//
//
//	public String CallableToFuture(JSONObject json){
//
//		Callable <String> callable = new Callable<String>() {
//			@Override
//			public String call(){
//				System.out.println("当前线程："+Thread.currentThread().getName());
//				String stFjId = json.getString("stFjId");
//				String stApplyId = json.getString("stApplyId");
//				String fjName =json.getString("fjName");
//				String url = json.getString("url");
//				String time = json.getString("time");
//				//String 转 Timestamp
//				Timestamp saveTime = Timestamp.valueOf(time.toString());
//
//				String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,FJ_NAME,URL,TIME) values (?,?,?,?,?)";
//				Object[] objects = new Object[] { stFjId, stApplyId, fjName,url,saveTime };
//				RecordSet rs = SQL.execute(sql,objects);
//				int number = rs.TOTAL_RECORD_COUNT;
//				System.out.println("\n");
//				return String.valueOf(stFjId);
//			}
//		};
//
//		Future <String> future =  threadPoolExecutor.submit(callable);//异步调用执行方法
//		String result = "";
//		try {
//			 result = future.get();//阻塞式获取结果
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//
//
//	public String threadToFutureTask(JSONObject json){
//
//		FutureTask <String> futureTask = new FutureTask<>(new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				System.out.println("当前线程："+Thread.currentThread().getName());
//				String stFjId = json.getString("stFjId");
//				String stApplyId = json.getString("stApplyId");
//				String fjName =json.getString("fjName");
//				String url = json.getString("url");
//				String time = json.getString("time");
//				//String 转 Timestamp
//				Timestamp saveTime = Timestamp.valueOf(time.toString());
//
//				String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,FJ_NAME,URL,TIME) values (?,?,?,?,?)";
//				Object[] objects = new Object[] { stFjId, stApplyId, fjName,url,saveTime };
//				RecordSet rs = SQL.execute(sql,objects);
//				int number = rs.TOTAL_RECORD_COUNT;
//				System.out.println("\n");
//				return String.valueOf(stFjId);
//			}
//		});
//
//		new Thread(futureTask).start();
//		String result="";
//		try {
//			 result = futureTask.get();//阻塞式获取结果
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//
//
//}
