//package com.wondersgroup.utils.threads.thresdPool_MQ_Impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.common.util.concurrent.*;
//import com.wondersgroup.utils.threads.thresdPool_MQ_Impl.rabbitMQ.SendData;
//import org.checkerframework.checker.nullness.qual.Nullable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//
//import java.sql.Timestamp;
//import java.util.concurrent.*;
//
//@Service
//public class ListenableFutureService {
//
//    @Autowired
//   private SendData sendData;
//
//    public String ListenableFutureService(JSONObject json){
//
//        String stFjId = json.getString("stFjId");
//        String stApplyId = json.getString("stApplyId");
//        String fjName =json.getString("fjName");
//        String url = json.getString("url");
//        String time = json.getString("time");
//
//        ThreadPoolExecutor threadPoolExecutor = getThreadPool();
//
//        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(threadPoolExecutor);
//
//        ListenableFuture listenableFuture = listeningExecutorService.submit(new Callable<String>() {
//
//            //String 转 Timestamp
//            Timestamp saveTime = Timestamp.valueOf(time);
//            @Override
//            public String call(){
//                System.out.println("当前线程："+Thread.currentThread().getName());
//
//                try {
//                    if(stApplyId.equals("5")||stApplyId.equals("10")||stApplyId.equals("15")||stApplyId.equals("20")){
//                        int i = 1/0;
//                    }
//
//                    String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,FJ_NAME,URL,TIME) values (?,?,?,?,?)";
//                    Object[] objects = new Object[] { stFjId, stApplyId, fjName,url,saveTime };
//                    RecordSet rs = SQL.execute(sql,objects);
//                    return String.valueOf(stFjId);
//                } catch (Exception e) {
//                    //发生异常将任务发送到RabbitMQ
//                    sendData.sendData("threadPool_queue",json.toString());
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        });
//
//
//        Futures.addCallback(listenableFuture,new FutureCallback<String>() {
//            @Override
//            public void onSuccess(@Nullable String s) {
//
//                System.out.println("成功回调！");
//                System.out.println(s);
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//
//                System.out.println("异常回调！");
//                throwable.printStackTrace();
//            }
//        }, listeningExecutorService);
//
//        listeningExecutorService.shutdown();
//
//        return stFjId;
//
//    }
//
//
//    //自定义线程池
//    public static ThreadPoolExecutor getThreadPool(){
//
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
//                1,2,60, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),
//                new MyRejectedExecutionHandler());
//
//        return threadPoolExecutor;
//    }
//
//
//
//    public synchronized String getData(){
//
//        try {
//            String sql = "select ST_APPLY_ID from DANGAN_FJ where ST_FJ_ID =?";
//            Object[] objects = new Object[] {"num"};
//            RecordSet rs = SQL.execute(sql,objects);
//            String num ="";
//            while (rs.next()){
//
//                num= rs.getString("ST_APPLY_ID");
//            }
//
//            String num2 =String.valueOf(Integer.parseInt(num)+1);
//            System.out.println("num2  :"+num2);
//            String sql2 = "update DANGAN_FJ set ST_APPLY_ID = ? where ST_FJ_ID = ?";
//            Object[] objects2 = new Object[] {num2,"num"};
//            RecordSet rs2 = SQL.execute(sql2,objects2);
//            int number = rs2.TOTAL_RECORD_COUNT;
//            if(number==1){
//                return num;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//}
