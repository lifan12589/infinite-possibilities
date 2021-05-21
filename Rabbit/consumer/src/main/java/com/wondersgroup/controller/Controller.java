package com.wondersgroup.controller;

import com.wondersgroup.sender.RabbitSender;
import com.wondersgroup.service.impl.OrderServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private RabbitSender sender; //加入OrderService bean
	
	//对外开放的接口，地址为：http://127.0.0.1:8090/queryOrderInfo?orderId=123456
	@RequestMapping("/queryOrderInfo")
	public String queryOrderInfo(@RequestParam(required = false) String orderId){
		try {
			//调用订单查询的service，并将查询结果返回给调用方(userService)
			 sender.send("topic.orderReceive",orderId);
//			rabbitTemplate.convertAndSend("exchange","topic.orderReceive","keys");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Autowired
	private RabbitTemplate rabbitTemplate;
	@RequestMapping("/queryOrderInfo.do")
	public String Server(){

		String keys = "keys";
		rabbitTemplate.convertAndSend("exchange","topic.orderReceive",keys);
		return "true";
	}



}
