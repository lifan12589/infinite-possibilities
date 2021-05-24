//package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rocketMQ;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//
//import javax.annotation.PostConstruct;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class ConsumerMQ {
//
//    @Value("${rocket.topic}")
//    private String topic;
//
//    @Value("${rocket.consumer.groupName}")
//    private String groupName;
//
//    @Value("${rocket.namesrvAddr}")
//    private String namesrvAddr;
//
//    private DefaultMQPushConsumer consumer;
//
//    @PostConstruct
//    public void listenToSave(){
//        System.out.println("进入 consumer .....");
//
//        try {
//            consumer = new DefaultMQPushConsumer(groupName);
//
//            consumer.setNamesrvAddr(namesrvAddr);
//
//            consumer.subscribe(topic,"*");
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//
//                for (MessageExt mess: list) {
//
//                    System.out.println("从 RocketMQ 监听到的失败办件 ："+(new String(mess.getBody())));
//
//                    JSONObject json = JSONObject.parseObject(new String(mess.getBody()));
//
//                    String stFjId = json.getString("stFjId");
//                    String stApplyId = json.getString("stApplyId");
//                    String time = json.getString("time");
//
//                    //String类型的时间戳 转 sql.Timestamp
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String Time = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
//                    Timestamp saveTime = Timestamp.valueOf(Time);
//
//                    String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,TIME) values (?,?,?)";
//                    Object[] objects = new Object[] { stFjId, stApplyId,saveTime };
//                    RecordSet rs = SQL.execute(sql,objects);
//
//                    try { Thread.sleep(5000); }
//                    catch (InterruptedException e) { e.printStackTrace();}
//
//                }
//
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//
//
//
//        try {
//            consumer.setConsumeThreadMax(1);
//
//            consumer.setConsumeThreadMin(1);
//
//            consumer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//        System.out.println("RocketMQ ---- consumer 开启！");
//
//    }
//
//
//
//}
