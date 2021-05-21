package com.infinitePossibilities.wyc.controller;

import com.infinitePossibilities.wyc.entity.OrderBase;
import com.infinitePossibilities.wyc.service.OrderService;
import com.infinitePossibilities.wyc.util.SnowFlake;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/create")
    public void createOrder() throws MQClientException {
        OrderBase orderBase = new OrderBase();
        String id = SnowFlake.nextId()+"";
        String orderNo = id+"_orderNo";
        orderBase.setId(id);
//        orderBase.setOrderNo(orderNo);
        orderService.createOrder(orderBase);
    }
}