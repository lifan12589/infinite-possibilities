package com.wondersgroup.receive;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class OrderInfoReceive {
	//接收从topic.orderReceive队列的数据（主要存放了服务端订单查询的结果）
    @RabbitListener(queues="topic.orderReceive")
    public void process1(String orderInfo) {    //用User作为参数
        System.out.println("监听%%%====topic.orderReceive  队列取到的  orderInfo ：========:"+orderInfo);
    }
       
}