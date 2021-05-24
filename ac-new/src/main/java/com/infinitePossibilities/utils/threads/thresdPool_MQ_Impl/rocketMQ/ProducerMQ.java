//package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rocketMQ;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.MQProducer;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class ProducerMQ {
//
//    @Value("${rocket.topic}")
//    private String topic;
//
//    @Value("${rocket.producer.groupName}")
//    private String groupName;
//
//    @Value("${rocket.namesrvAddr}")
//    private String namesrvAddr;
//
//    public void sendData(JSONObject json){
//
////        DefaultMQProducer producer = new DefaultMQProducer(groupName);
////        producer.setNamesrvAddr(namesrvAddr);
////        producer.start();
//
//        MQProducer producer = null;
//        try {
//            producer = MyProducerInstance.getProducerInstance().getInstance(namesrvAddr,groupName,"");
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("RocketMQ ---- producer 开启！");
//
//        try {
//            producer.send(new Message(topic,json.toString().getBytes()));
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        } catch (RemotingException e) {
//            e.printStackTrace();
//        } catch (MQBrokerException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
