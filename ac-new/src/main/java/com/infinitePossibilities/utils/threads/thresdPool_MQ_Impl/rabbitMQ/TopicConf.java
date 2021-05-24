//package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rabbitMQ;
//
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//@Configuration
//public class TopicConf {
//
//    @Bean(name = "threadPool_queue")
//    public Queue queueMess(){
//        System.out.println("系统启动时，创建一个 threadPool_queue 队列到 RabbitMQ");
//        return new Queue("threadPool_queue");
//    }
//
//    @Bean(name = ("threadPool_Topic"))
//    public TopicExchange topicExchange(){
//        System.out.println("系统启动时，创建一个 threadPool_Topic 交换机到 RabbitMQ");
//        return new TopicExchange("threadPool_Topic");
//    }
//
//    @Bean
//    Binding bindingQueueToExchangeMessage(@Qualifier("threadPool_queue")Queue threadPool_queue,TopicExchange threadPool_Topic){
//        System.out.println("将 threadPool_queue 与 threadPool_Topic 绑定起来");
//        return BindingBuilder.bind(threadPool_queue).to(threadPool_Topic).with("threadPool_queue");
//    }
//
//
//}
