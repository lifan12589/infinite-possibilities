package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer3 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq1");

        //设置nameserver地址：
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        Message message1 = new Message("myTopic001","rocketMq1 第一次发送 sendOneway".getBytes());

        // 单向消息
        //只发送消息，不等待服务器响应，只发送请求不等待应答。此方式发送消息的过程耗时非常短，一般在微秒级别。
        producer.sendOneway(message1);

//        producer.shutdown();
        System.out.println("生产者下线！");

    }


}
