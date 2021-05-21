package com.online.taxi.order.redLock;


/**
 * @author yueyi2019
 */
public interface RedLockService {

    public String grabOrder(long orderId);
}
