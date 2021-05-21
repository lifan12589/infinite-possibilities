package com.infinitePossibilities.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class Consumer {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketMq1");

        //设置nameserver地址
        consumer.setNamesrvAddr("127.0.0.1:9876");

        // 每个consumer 关注一个topic

        // topic 关注的消息的地址
        // 过滤器 * 表示不过滤
        consumer.subscribe("myTopic001","*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for(MessageExt message: list){
                    System.out.println(new String(message.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.start();

        System.out.println("消费者  start....");



    }

}
