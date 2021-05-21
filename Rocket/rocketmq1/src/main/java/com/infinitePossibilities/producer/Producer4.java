package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer4 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq");

        //����nameserver��ַ��
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        Message message1 = new Message("myTopic001","TAG-B","KEY-B","rocketMq1 ��һ�η��� TAG-B".getBytes());

        // ������Ϣ
        //
        producer.send(message1);

        producer.shutdown();
        System.out.println("���������ߣ�");

    }


}
