//package com.wondersgroup.demoRabbit;
//
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.stereotype.Component;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//
///**
// * 消费多线程
// */
//@Component
//public class Receiver5 {
//    boolean flag=true;
//    /**
//     * @RabbitListener:加了该注解的方法表示该方法是一个消费者
//     * concurrency：并发数量。可在配置文件加 spring.rabbitmq.listener.simple.concurrency: 5
//     */
//    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(value = "myQueue"),
//                    arguments = {
//                            @Argument(name = "x-dead-letter-exchange", value = "myExchange1_dlx"),
//                            @Argument(name = "x-dead-letter-routing-key", value = "routingKey4")},
//                    exchange = @Exchange(value = "Exchange"),
//                    key = "routingKey"
//            )
////   ,concurrency =  "5"
//    )
//    public void process(Message message, Channel channel) throws Exception {
//        System.out.println("myQueue:" +  new String(message.getBody()));
//        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//    }
//    /**
//     * 死信交换机
//     */
//    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(value = "myQueueSX"),
//                    exchange = @Exchange(value = "myExchange1_dlx"),
//                    key = "routingKey4"
//            ))
//    public void process9(Message message){
//        System.out.println("myQueueSX:" +  new String(message.getBody()));
//    }
//
//
//
//
//
//
//
//
//
//}
