package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer2 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq1");

        //����nameserver��ַ��
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        // �첽�ɿ���Ϣ
        // �����������ȴ�broker��ȷ��
        //�����¼�������ʽ����broker���ص�ȷ��
        Message message1 = new Message("myTopic001","rocketMq1 ��һ�η���".getBytes());

        producer.send(message1, new SendCallback() {
            public void onSuccess(SendResult sendResult) {

                System.out.println("���ͳɹ���");
                System.out.println("���ͽ�� ��"+sendResult);
            }

            public void onException(Throwable throwable) {

                throwable.printStackTrace();
                System.out.println("����ʧ�ܣ�");
            }
        });


        //�����������������쳣
//        producer.shutdown();
        System.out.println("���������ߣ�");

    }


}
