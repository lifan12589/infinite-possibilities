//package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rabbitMQ;
//
//import com.alibaba.fastjson.JSONObject;
//import com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rocketMQ.ProducerMQ;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class ListenerAndSaveData {
//
//    @Autowired
//    private ProducerMQ producerMQ;
//
//    @RabbitListener(queues="threadPool_queue")
//    public void listenToSave(String messageJson){
//
//        System.out.println("从 threadPool_queue 队列监听到的失败办件 ："+messageJson);
//
//        try { Thread.sleep(5000); }
//        catch (InterruptedException e) { e.printStackTrace();}
//
//        JSONObject json = JSONObject.parseObject(messageJson);
//        try {
//
//            String stFjId = json.getString("stFjId");
//            String stApplyId = json.getString("stApplyId");
//            String time = json.getString("time");
//
//            //String类型的时间戳 转 sql.Timestamp
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String Time = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
//            Timestamp saveTime = Timestamp.valueOf(Time);
//
//            if(stApplyId.equals("15")||stApplyId.equals("20")){
//                int i = 1/0;
//            }
//
//            String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,TIME) values (?,?,?)";
//            Object[] objects = new Object[] { stFjId, stApplyId,saveTime };
//            RecordSet rs = SQL.execute(sql,objects);
//        } catch (Exception e) {
//            //RabbitMQ 发送失败 就往 RocketMQ 发送
//            try {
//                System.out.println("RabbitMQ 发送异常，  切换到 RocketMQ   异常为 ："+e );
//                producerMQ.sendData(json);
//            } catch (Exception e1) {
//                System.out.println("RocketMQ 也发送失败！ 数据为："+json.toString()+"/n"+"异常为 ："+e1);
//            }
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//}
