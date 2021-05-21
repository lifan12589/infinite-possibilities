package com.infinitePossibilities.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer3 {
    public static void main(String[] args) throws Exception {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("MQ-SX-2Group");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.subscribe("MQ2-SX-Topic","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for (MessageExt mes: list) {

                    System.out.println("mes : "+new String(mes.getBody())+ " Thread:"  + Thread.currentThread().getName() + " queueid:" + mes.getQueueId());
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //使用一个线程去消费，保证顺序
        consumer.setConsumeThreadMax(1);
        consumer.setConsumeThreadMin(1);

        consumer.start();
        System.out.println("Consumer -SX- start...");
    }
}
