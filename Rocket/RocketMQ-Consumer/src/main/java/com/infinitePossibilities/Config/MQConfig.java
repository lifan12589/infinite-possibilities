//package com.infinitePossibilities.Config;
//
//import com.infinitePossibilities.web.MyMessageListener;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MQConfig {
//
//    @Value("${rocketmq.consumer.namesrvAddr}")
//    private String namesrvAddr;
//    @Value("${rocketmq.consumer.groupName}")
//    private String groupName;
//    @Value("${rocketmq.consumer.topics}")
//    private String topics;
//
//
//    @Bean
//    public DefaultMQPushConsumerImpl getRocketMQConsumer(){
//
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.groupName);
//
//        try {
//            consumer.setNamesrvAddr(this.namesrvAddr);
//            consumer.subscribe(this.topics,"*");
//
//            consumer.registerMessageListener(new MyMessageListener());
//
//            consumer.start();
//        } catch (MQClientException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//
//
//
//}
