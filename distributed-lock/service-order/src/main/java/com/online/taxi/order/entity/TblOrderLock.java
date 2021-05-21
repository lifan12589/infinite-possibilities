package com.online.taxi.order.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * tbl_order_lock
 * @author 
 */
@Data
public class TblOrderLock implements Serializable {
    private Integer orderId;

    private Integer driverId;

    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}