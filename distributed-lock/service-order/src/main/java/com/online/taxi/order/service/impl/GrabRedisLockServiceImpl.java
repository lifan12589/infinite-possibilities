package com.online.taxi.order.service.impl;

import com.online.taxi.order.service.GrabService;
import com.online.taxi.order.service.OrderService;
import com.online.taxi.order.service.RenewGrabLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author
 */
@Service("grabRedisLockService")
public class GrabRedisLockServiceImpl implements GrabService {

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	OrderService orderService;

	@Autowired
	RenewGrabLockService renewGrabLockService;
	
    @Override
    public String grabOrder(int orderId , int driverId){
		System.out.println("redis锁");
        //生成key
    	String lock = "order_"+(orderId+"");
    	/*
    	 *  情况一，如果锁没执行到释放，比如业务逻辑执行一半，运维重启服务，或 服务器挂了，没走 finally，怎么办？
    	 *  加超时时间
    	 *  setnx
    	 */
//    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"");
//    	if(!lockStatus) {
//    		return null;
//    	}
    	
    	/*
    	 *  情况二：加超时时间,会有加不上的情况，运维重启
    	 */
//    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"");
//    	stringRedisTemplate.expire(lock.intern(), 30L, TimeUnit.SECONDS);
//    	if(!lockStatus) {
//    		return null;
//    	}
    	
    	/*
    	 * 情况三：超时时间应该一次加，不应该分2行代码，
    	 * 
    	 */
    	boolean lockStatus = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"", 20L, TimeUnit.SECONDS);

    	//加锁失败后：要么循环去加锁，要么直接返回null
		if(!lockStatus) {

			System.out.println(driverId+"  加锁失败！ " +lockStatus);
			while (!lockStatus){
				System.out.println(driverId+"再次加锁");
				Boolean type = stringRedisTemplate.opsForValue().setIfAbsent(lock.intern(), driverId+"", 20L, TimeUnit.SECONDS);
				if(type==true){
					lockStatus=true;
					break;
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

//    		return null;
		}

		// 开个子线程，原来时间N，每个n/3，去续上n
		renewGrabLockService.renewLock(lock.intern(),driverId+"",15);

		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
//			System.out.println("司机:"+driverId+" 执行抢单逻辑");
			
            boolean b = orderService.grab(orderId, driverId);
            if(b) {
//            	System.out.println("司机:"+driverId+" 抢单成功");
            }else {
//            	System.out.println("司机:"+driverId+" 抢单失败");
            }
            
        } finally {
        	/**
        	 * 这种释放锁有，可能释放了别人的锁。
        	 */
//        	stringRedisTemplate.delete(lock.intern());
        	
        	/**
        	 * 下面代码避免释放别人的锁
        	 */
        	if((driverId+"").equals(stringRedisTemplate.opsForValue().get(lock.intern()))) {
				stringRedisTemplate.delete(lock.intern());
				System.out.println("锁释放 ："+lock);
			}
        }
        return null;
    }
}