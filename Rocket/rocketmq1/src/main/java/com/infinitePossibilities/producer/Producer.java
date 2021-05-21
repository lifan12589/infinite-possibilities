package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;

public class Producer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq1");

        //����nameserver��ַ��
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        //topic ��Ϣ��Ҫ���͵ĵ�ַ
        //body ������Ϣ����
        Message message1 = new Message("myTopic001","rocketMq1 ��һ�η���".getBytes());
        Message message2 = new Message("myTopic001","rocketMq1 �ڶ��η���".getBytes());
        Message message3 = new Message("myTopic001","rocketMq1 �����η���".getBytes());

        ArrayList arrayList = new ArrayList();
        arrayList.add(message1);
        arrayList.add(message2);
        arrayList.add(message3);

        SendResult result = producer.send(arrayList);

        System.out.println("���ͽ�� ��"+result);
        producer.shutdown();
        System.out.println("���������ߣ�");

    }


}
