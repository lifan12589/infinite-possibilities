package com.online.taxi.order.redLock;

import com.online.taxi.order.dao.SaveInfoMapper;
import com.online.taxi.order.entity.SaveInfo;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author
 */
@Service("grabRedisRedissonRedLockLockService")
public class RedLockLockServiceImpl implements RedLockService {

    // 红锁
    @Autowired
    @Qualifier("redissonRed1")
    private RedissonClient redissonRed1;
    @Autowired
    @Qualifier("redissonRed2")
    private RedissonClient redissonRed2;
    @Autowired
    @Qualifier("redissonRed3")
    private RedissonClient redissonRed3;
    @Autowired
    @Qualifier("redissonRed4")
    private RedissonClient redissonRed4;
    @Autowired
    @Qualifier("redissonRed5")
    private RedissonClient redissonRed5;
//
//    @Autowired
//    private Redisson redisson;

    @Autowired
    SaveInfoMapper saveInfoMapper;

    @Override
    public String grabOrder(long orderId ){
//        System.out.println("红锁实现类");
        System.out.println(orderId+" : 来加锁");
        //生成key
        String lockKey = ("lock_").intern();

        //红锁 redis son
        RLock rLock1 = redissonRed1.getLock(lockKey);
        RLock rLock2 = redissonRed2.getLock(lockKey);
        RLock rLock3 = redissonRed3.getLock(lockKey);
        RLock rLock4 = redissonRed4.getLock(lockKey);
        RLock rLock5 = redissonRed5.getLock(lockKey);
        RedissonRedLock rLock = new RedissonRedLock(rLock1,rLock2,rLock3,rLock4,rLock5);

//        boolean b1;
        try {

            boolean b1 = rLock.tryLock();

            if (b1){
                System.out.println(orderId+" : 加锁成功");
                // 此代码默认 设置key 超时时间30秒，过10秒，再延时
                boolean type = sendData(orderId+"");
               if(type){
                   System.out.println(orderId+" : 添加成功!!!!!!!!!!!!");
               }else {
                   System.out.println(orderId+" : 添加失败!!!!!!!!!!!!");
               }
            }else {
                System.out.println(orderId+" : 加锁失败#####");
                try {
                    Thread.sleep(100);
                    grabOrder(orderId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println("加锁过程异常 ："+e);
        }finally {

                System.out.println(orderId+" : 释放锁~~~~~~~~！");
                rLock.unlock();

        }
        return null;
    }




    public Boolean sendData(String orderId){

        Timestamp saveTime=new Timestamp(new Date().getTime());
        SaveInfo info = new SaveInfo();
        info.setId(orderId);
        info.setApplyno("004098020000002");
        info.setUserName("测试2");
        info.setUserNumber("002");
        info.setAddress("静安区");
        info.setSaveTime(saveTime);
        info.setType(orderId);
        info.setCount("count");
        int mu = saveInfoMapper.insert(info);
        if(mu!=1){
           return false;
        }
        return true;
    }










}