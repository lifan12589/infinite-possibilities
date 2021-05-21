//package com.infinitePossibilities.service;
//
//import org.apache.rocketmq.client.exception.MQBrokerException;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.remoting.exception.RemotingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MQService {
//
//    @Autowired
//    DefaultMQProducer defaultMQProducer;
//
//    public Object sendMessage(){
//
//
//            List list = new ArrayList();
//            Message message1 = new Message("springBootTopice",("springBootTopic测试数据第一条！:").getBytes());
//            Message message2 = new Message("springBootTopice",("springBootTopic测试数据第二条！:").getBytes());
//            Message message3 = new Message("springBootTopice",("springBootTopic测试数据第三条！:").getBytes());
//            list.add(message1);
//            list.add(message2);
//            list.add(message3);
//
//            try {
//                return defaultMQProducer.send(list);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        return null;
//    }
//
//
//
//
//}
