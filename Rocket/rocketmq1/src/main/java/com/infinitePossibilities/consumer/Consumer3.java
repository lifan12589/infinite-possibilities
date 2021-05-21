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

        //����nameserver��ַ��
        consumer.setNamesrvAddr("127.0.0.1:9876");

        //ÿ��cconsumerֻ�ܹ�עһ��topic

        //topic ��ע����Ϣ��ַ
        //������ * ��������
        // tag selector ��һ��group�е������ߣ����������䣬Ҫ����ͳһ
        MessageSelector selector =  MessageSelector.bySql("num >= 18 and num <= 28");
        consumer.subscribe("myTopic001",selector);

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for(MessageExt message: list){
                    System.out.println(new String(message.getBody()));
                }

                // Ĭ������� ������Ϣֻ�ᱻ һ��consumer ���ѵ� ��Ե�
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.start();

        System.out.println("������  start....");



    }

}
