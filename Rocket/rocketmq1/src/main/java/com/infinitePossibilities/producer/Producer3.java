package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class Producer3 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq1");

        //����nameserver��ַ��
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        Message message1 = new Message("myTopic001","rocketMq1 ��һ�η��� sendOneway".getBytes());

        // ������Ϣ
        //ֻ������Ϣ�����ȴ���������Ӧ��ֻ�������󲻵ȴ�Ӧ�𡣴˷�ʽ������Ϣ�Ĺ��̺�ʱ�ǳ��̣�һ����΢�뼶��
        producer.sendOneway(message1);

//        producer.shutdown();
        System.out.println("���������ߣ�");

    }


}
