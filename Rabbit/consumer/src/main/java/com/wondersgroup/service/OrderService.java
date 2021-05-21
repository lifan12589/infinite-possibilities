

package com.wondersgroup.service;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

	String queryOrderInfo(String orderId) throws Exception;
}