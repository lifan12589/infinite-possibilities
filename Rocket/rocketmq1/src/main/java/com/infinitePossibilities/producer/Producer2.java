package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class Producer2 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("rocketMq1");

        //设置nameserver地址：
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();

        // 异步可靠消息
        // 不会阻塞，等待broker的确认
        //采用事件监听方式接受broker返回的确认
        Message message1 = new Message("myTopic001","rocketMq1 第一次发送".getBytes());

        producer.send(message1, new SendCallback() {
            public void onSuccess(SendResult sendResult) {

                System.out.println("发送成功！");
                System.out.println("发送结果 ："+sendResult);
            }

            public void onException(Throwable throwable) {

                throwable.printStackTrace();
                System.out.println("发送失败！");
            }
        });


        //测试先下线抛链接异常
//        producer.shutdown();
        System.out.println("生产者下线！");

    }


}
