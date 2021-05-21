
package com.wondersgroup.service.impl;

import com.wondersgroup.dao.OrderDao;
import com.wondersgroup.model.Order;

import com.wondersgroup.sender.RabbitSender;
import com.wondersgroup.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RabbitSender sender;

        public String queryOrderInfo(String orderid) {
            sender.send("orderMessage",orderid);
            return orderid+" ###进入 orderMessage 队列~~~~~";
        }



}

