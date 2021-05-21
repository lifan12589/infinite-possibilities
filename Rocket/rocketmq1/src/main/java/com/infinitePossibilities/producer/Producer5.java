package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer5 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq1");

        //设置nameserver地址：
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        for(int i=1;i<=100;i++){

            Message message1 = new Message("myTopic001","TAG-B","KEY-B",("rocketMq1 第一次发送 TAG-B"+i).getBytes());
            message1.putUserProperty("num",String.valueOf(i));
            producer.send(message1);
        }


        producer.shutdown();
        System.out.println("生产者下线！");

    }


}
