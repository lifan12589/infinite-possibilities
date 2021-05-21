//package com.infinitePossibilities.web;
//
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.common.message.MessageExt;
//
//import java.util.List;
//
//public class MyMessageListener implements MessageListenerConcurrently {
//    @Override
//    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//
//        System.out.println("开始接收数据！");
//
//        for (MessageExt messageExt : list){
//
//            System.out.println(new String(messageExt.getBody()));
//
//        }
//        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//    }
//}
