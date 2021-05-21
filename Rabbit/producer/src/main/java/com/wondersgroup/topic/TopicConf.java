package com.wondersgroup.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//配置类，随系统启动时，根据需求创建交换器和队列， 用来接收服务端发送过来的数据
@Configuration
public class TopicConf {

	    //系统启动时：创建一个message的队列到rabbitMQ
        @Bean(name="message")
        public Queue queueMessage() {
            System.out.println("系统启动时：创建一个topic.order的队列到rabbitMQ");
            return new Queue("topic.order");
        }

        //系统启动时：创建一个exchange的交换器到rabbitMQ
        @Bean
        public TopicExchange exchange() {
            return new TopicExchange("exchange");
        }
        //系统启动时：将exchange的交换器与队列绑定
        @Bean
        Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
            System.out.println("系统启动时：将exchange的交换器与topic.order队列绑定");
            return BindingBuilder.bind(queueMessage).to(exchange).with("topic.order");
        }

}
