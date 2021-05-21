package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;

public class Producer2 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("MQ2Group");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        //异步发送 重试次数 系统默认是2
        producer.setRetryTimesWhenSendAsyncFailed(1);
        //同步发送 重试次数 系统默认是2
//        producer.setRetryTimesWhenSendFailed(1);
        producer.send(new Message("MQ2Topic","回调消息!".getBytes()));
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
//        producer.shutdown();
        System.out.println("生产者下线！");

    }
}
