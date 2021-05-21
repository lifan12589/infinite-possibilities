//package com.infinitePossibilities.Config;
//
//
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MQConfig {
//
//    public static final Logger logger = LoggerFactory.getLogger(MQConfig.class);
//
//    @Value("${rocketmq.producer.groupName}")
//    private String groupName;
//
//    @Value("${rocketmq.producer.namesrvAddr}")
//    private String nameSrvAddr;
//
//    @Bean
//    public DefaultMQProducer getRocketMQProducer (){
//
//        DefaultMQProducer producer;
//
//        producer = new DefaultMQProducer(this.groupName);
//        producer.setNamesrvAddr(this.nameSrvAddr);
//
////        System.out.println( groupName+"/"+nameSrvAddr );
//        try {
//            producer.start();
//
//            System.out.println("producer  start....");
//
//        } catch (MQClientException e) {
//            System.out.println(String.format("producer is error {}", e.getMessage(), e));
//        }
//
//        return producer;
//    }
//
//}
