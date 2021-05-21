package com.wondersgroup.dao;


import com.wondersgroup.model.Order;

public interface OrderDao {
	//查询方法
    Order queryOrderInfo(String orderid);
   
}