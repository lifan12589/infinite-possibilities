package com.wondersgroup.sender;



import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//定义队列发送的方法
@Component
public class RabbitSender {
	//注入AmqpTemplate
	@Autowired
	private AmqpTemplate template;

	public void send(String queueName,String orderInfo) {
		//由AmqpTemplate将数据发送到指定的队列
		System.out.println("send方法两个参数："+queueName+" / "+orderInfo);
		template.convertAndSend(queueName,orderInfo);
	}
	
}
