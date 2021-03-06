package com.wondersgroup.sender;


import java.util.Map;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//定义队列发送的方法
@Component
public class RabbitSender {
	//注入AmqpTemplate
	@Autowired
	private AmqpTemplate template;
	//由AmqpTemplate将数据发送到指定的队列
	public void send(String queueName,String orderId) {
		System.out.println("由AmqpTemplate将数据发送到指定的队列");
		template.convertAndSend(queueName, orderId);
	}
	//由AmqpTemplate将数据发送到指定的队列，主要用于发送对象
	public void sendObject(String queueName,Map user) {
		System.out.println("由AmqpTemplate将数据发送到指定的队列，主要用于发送对象");
		template.convertAndSend(queueName,user);
    }
	//由AmqpTemplate将数据发送到交换机和队列
	public void sendTopic(String exchange, String queueName, String orderId) {
		System.out.println(Thread.currentThread().getName()+":  进入sendTopic方法");
		System.out.println("%%%由AmqpTemplate将数据发送到交换机"+exchange+" 和队列 "+queueName);
        template.convertAndSend(exchange,queueName,orderId);
    }
}
