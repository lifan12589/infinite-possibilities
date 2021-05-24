//package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rabbitMQ;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SendData {
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    public void sendData(String queuename,String data){
//
//        System.out.println("由AmqpTemplate将数据发送到指定的队列");
//        amqpTemplate.convertAndSend(queuename,data);
//
//    }
//
//
//
//
//
//}
