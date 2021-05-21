package com.infinitePossibilities.wyc.entity;

import java.io.Serializable;

/**
 * tbl_points
 * @author 
 */
public class TblPoints implements Serializable {
    /**
     * 主键
     */
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 用户id
     */
    private String userId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 积分
     */
    private String points;

    /**
     * 备注
     */
    private String remarks;

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getPoints() {
        return points;
    }

    public String getRemarks() {
        return remarks;
    }

    private static final long serialVersionUID = 1L;
}