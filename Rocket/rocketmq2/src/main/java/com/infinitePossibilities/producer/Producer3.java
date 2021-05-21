package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer3 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("MQ-SX-2Group");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        producer.send(new Message("MQ2-SX-Topic","顺序--消息!".getBytes()));
        producer.shutdown();
        System.out.println("生产者下线！");

    }
}
