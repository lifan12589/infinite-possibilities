package com.online.taxi.order.service.impl;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.online.taxi.order.lock.MysqlLock;
import com.online.taxi.order.lock.RedisLock;
import com.online.taxi.order.service.GrabService;
import com.online.taxi.order.service.OrderService;

/**
 * @author
 */
@Service("grabRedisRedissonService")
public class GrabRedisRedissonServiceImpl implements GrabService {

	@Autowired
	RedissonClient redissonClient;

//	@Autowired
//	Redisson redisson;

	@Autowired
	OrderService orderService;
	
    @Override
    public String grabOrder(int orderId , int driverId){
        //生成key
    	String lock = "order_"+(orderId+"");
        System.out.println("key: "+lock);
    	RLock rlock = redissonClient.getLock(lock.intern());
//        RLock lock1 = redisson.getLock(lock.intern());

        try {
    		// 此代码默认 设置key 超时时间30秒，过10秒，再延时
    		rlock.lock();
//            lock1.lock();

            try {
                System.out.println("执行逻辑: "+driverId);
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
//            	System.out.println("司机:"+driverId+" 抢单成功");
            }else {
//            	System.out.println("司机:"+driverId+" 抢单失败");
            }
            
        } finally {
            System.out.println("释放锁："+driverId);
        	rlock.unlock();
//            lock1.unlock();
        }
        return null;
    }
}