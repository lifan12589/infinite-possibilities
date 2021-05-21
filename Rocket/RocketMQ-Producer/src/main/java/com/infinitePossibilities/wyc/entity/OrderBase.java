package com.infinitePossibilities.wyc.entity;

import java.io.Serializable;

/**
 * order_base
 * @author 
 */
public class OrderBase implements Serializable {

    private String id;

    private String orderNo;

    public void setId(String id) {
        this.id = id;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getId() {
        return id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    private static final long serialVersionUID = 1L;
}