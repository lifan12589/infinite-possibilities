package com.infinitePossibilities.CompletableFuture;

import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class CompletableFutureService {

static class Reques{
    String orderCode;
    String serialNo;
    CompletableFuture<Map<String ,Object>> future;
}

	static LinkedBlockingDeque<Reques> queue = new LinkedBlockingDeque<>();

    public static Map<String ,Object> query(String orderCode,String serialNo) throws ExecutionException, InterruptedException {
//        String serialNo = UUID.randomUUID().toString();
        CompletableFuture<Map<String ,Object>> future = new CompletableFuture<>();

        Reques request = new Reques();
        request.future = future;
        request.serialNo = serialNo;
        request.orderCode = orderCode;
        queue.put(request);
        return future.get();
    }

//    @PostConstruct
    public void init(){
    	System.out.println("进入定时任务...");
        //创建执行定时任务的线程池
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
               int size =queue.size();
               if(size ==0){
                   return;
               }
               System.out.println("size-------"+size);
//           List<Map<String,Object>> params = new ArrayList<>();   
               JSONObject json = new JSONObject();
           ArrayList<Reques> req = new ArrayList<>();
               for (int i=0;i<size;i++){
					try {
						   Reques reques = queue.take();
//						   System.out.println("reques   -- "+reques);
//						   Map<String ,Object> map = new HashMap<>();
//		                   map.put("serialNo",reques.serialNo);
//		                   map.put("orderCode",reques.orderCode);
//		                   params.add(map);
						   json.put("serialNo", reques.serialNo);
						   json.put("orderCode",reques.orderCode);
		                   req.add(reques);
		                   
		                   List<Map<String,Object>> res = sendDB(json);
		                   for(Reques requ : req){
		                	   String serialNo = requ.serialNo;
		                	   for (Map<String, Object> reque : res) {
//		                		   System.out.println("判断    serialNo   ===  "+serialNo.equals(reque.get("serialNo").toString()));
		    					if(serialNo.equals(reque.get("serialNo").toString())){
		    						requ.future.complete(reque);
		    						break;
		    					}
		    				}
		                 }
		                   
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
               }
            
            }
        },0,10,TimeUnit.SECONDS);//10秒查看一下队列
    }

    
    public static List<Map<String,Object>> sendDB(JSONObject json) {
    	
    	List<Map<String,Object>> list = new ArrayList<>();
    	Map<String,Object> fhMap = new HashMap<>();
//    	 int count = 1;
//    	 String st_fj_id = json.get("orderCode").toString();
////    	 Long time = System.currentTimeMillis();
//         String insertSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
//         Object[] insertObject = new Object[] {count,st_fj_id};
//         RecordSet rs  = SQL.execute(insertSql,insertObject);
////         int number = rs.TOTAL_RECORD_COUNT;
//         System.out.println("serialNo:--->"+json.get("serialNo"));
////         System.out.println(Thread.currentThread().getId()+"    数据库影响行数  "+number+"    "+time);
         
         fhMap.put("serialNo",json.get("serialNo"));
         list.add(fhMap);
		return list;
		
	}
    
    
    
}
