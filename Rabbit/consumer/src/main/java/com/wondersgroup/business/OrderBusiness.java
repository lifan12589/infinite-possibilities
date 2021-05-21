package com.wondersgroup.business;

import java.util.Map;
import java.util.UUID;

import com.wondersgroup.sender.RabbitSender;
//import com.wondersgroup.service.OrderService;
import com.wondersgroup.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//实现接收信息的处理类，由此类从MQ取相关的数据，如果orderId,
@Component
public class OrderBusiness {
    @Autowired
	private OrderService orderService;
    @Autowired
	private RabbitSender rabbitSender;//将处理结果发送数据到队列
    
    //监听器监听指定的Queue
    @RabbitListener(queues="queue")    
    public void processC(String orderId) {
        System.out.println("%监听queue 队列取到的 orderId===:"+orderId);
        try {
			orderService.queryOrderInfo(orderId);
//            rabbitSender.send("topic.orderReceive", orderId.toString()+" &&从queue进入队列~~~~~");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    //监听指定queueObject队列，获取的数据为Map对象
    @RabbitListener(queues="queueObject")   
    public void process1(Map user) {    
        System.out.println("%%监听queueObject 队列取到的 user===:"+user);
        rabbitSender.send("topic.orderReceive", user.toString()+" &&从queueObject进入队列~~~~~");

    }
    //监听指定的topic.order队列，当此队列有数据时，数据就会被取走
    @RabbitListener(queues="topic.order")    
    public void process1(String orderId) {
        System.out.println("监听topic.order 队列取到的 orderId :"+orderId);
        try {
        	//根据从队列里获取到的orderId, 查询出订单信息
//			String order = orderService.queryOrderInfo(orderId);
            UUID id = UUID.randomUUID();


				//将查询出来的订单信息结果发送到topic.orderReceive队列，等待userService来获取
//				rabbitSender.send("topic.orderReceive", order.getOrderid()+"~"+order.getOrdermoney()+"~"+order.getOrderstatus()+"~"+order.getOrdertime());

            System.out.println("并由AmqpTemplate 发往 topic.orderReceive："+id);
            rabbitSender.send("topic.orderReceive", id.toString()+" &&从topic.order进入队列~~~~~");


		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

}