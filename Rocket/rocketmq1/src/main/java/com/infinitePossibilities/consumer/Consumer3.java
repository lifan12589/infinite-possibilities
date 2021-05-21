package com.infinitePossibilities.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class Consumer3 {

    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rocketMq1");

        //设置nameserver地址：
        consumer.setNamesrvAddr("127.0.0.1:9876");

        //每个cconsumer只能关注一个topic

        //topic 关注的消息地址
        //过滤器 * 代表不过滤
        // tag selector 在一个group中的消费者，都不能随便变，要保持统一
        MessageSelector selector =  MessageSelector.bySql("num >= 18 and num <= 28");
        consumer.subscribe("myTopic001",selector);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for(MessageExt message: list){
                    System.out.println(new String(message.getBody()));
                }

                // 默认情况下 这条消息只会被 一个consumer 消费到 点对点
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.start();

        System.out.println("消费者  start....");



    }

}
