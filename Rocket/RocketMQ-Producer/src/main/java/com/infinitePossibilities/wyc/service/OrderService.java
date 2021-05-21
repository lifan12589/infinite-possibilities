package com.infinitePossibilities.wyc.service;

import com.infinitePossibilities.wyc.entity.OrderBase;
import org.apache.rocketmq.client.exception.MQClientException;

import java.lang.reflect.InvocationTargetException;

public interface OrderService {

    public void createOrder(OrderBase order, String transactionId) throws InvocationTargetException, IllegalAccessException;

    public void createOrder(OrderBase order) throws MQClientException;
}