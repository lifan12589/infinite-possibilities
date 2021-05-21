package com.wondersgroup.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//配置类，随系统启动时，根据需求创建交换器和队列， 用来接收服务端发送过来的数据
@Configuration
public class DirectConf {
     @Bean
     public Queue queue() {
//          System.out.println("系统启动时：创建一个queue的队列到rabbitMQ");
          return new Queue("queue");
     }
     @Bean
     public Queue queueObject() {
//          System.out.println("系统启动时：创建一个queueObject的队列到rabbitMQ");
          return new Queue("queueObject");
     }
}
