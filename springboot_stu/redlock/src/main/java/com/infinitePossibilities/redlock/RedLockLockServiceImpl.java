package com.infinitePossibilities.redlock;

import com.infinitePossibilities.dao.SaveInfoMapper;
import com.infinitePossibilities.entity.SaveInfo;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;


@Service("RedLockLockService")
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


    @Autowired
    SaveInfoMapper saveInfoMapper;

    @Override
    public String grabOrder(String orderId ){
//        System.out.println(orderId+" : 来加锁");

        //生成key
        String lockKey = ("lock_red").intern();

        //红锁 redis son
        RLock rLock1 = redissonRed1.getLock(lockKey);
        RLock rLock2 = redissonRed2.getLock(lockKey);
        RLock rLock3 = redissonRed3.getLock(lockKey);
        RLock rLock4 = redissonRed4.getLock(lockKey);
        RLock rLock5 = redissonRed5.getLock(lockKey);
        RedissonRedLock rLock = new RedissonRedLock(rLock1,rLock2,rLock3,rLock4,rLock5);
        String count ="";
        boolean b1= true;
        boolean b2= true;
        try {
            //包含锁续命 默认设置key 超时时间30秒，过10秒，再延时
             b1 = rLock.tryLock();
            if (b1==true){
                System.out.println(orderId+" : 加锁成功");
                count = updataData();
                System.out.println(orderId+" 数据库返回结果 ："+count);
                if(count.equals("0")){
                    return "数据库总数已为0";
                }
            }else {
//                System.out.println(orderId+" : 加锁失败#####");
                b2 = false;
                try {
                    //未拿到锁，暂停一下再去加锁
                    Thread.sleep(100);
                    grabOrder(orderId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            System.out.println("加锁过程异常 ："+e);
        }finally {
            if(b2==true){
                System.out.println(orderId+" : 去解锁~~~~~");
                rLock.unlock();
            }
        }
        return count;
    }


    //数据库写入操作
    public Boolean sendData(String orderId){

        Timestamp saveTime=new Timestamp(new Date().getTime());
        SaveInfo info = new SaveInfo();
        info.setId(orderId);
        info.setApplyno("004098020000002");
        info.setUserName("user");
        info.setUserNumber("002");
        info.setAddress("上海");
        info.setSaveTime(saveTime);
        info.setType(orderId);
        info.setCount("count");
        int mu = saveInfoMapper.insert(info);
        if(mu!=1){
           return false;
        }
        return true;
    }


    //数据库减一操作
    public String updataData() {

        //查出id为1112的数据，判断需要减1的字段当前值
        SaveInfo save = saveInfoMapper.selectInfoByid("1112");
        String type = save.getType();
        if(type.equals("0")){
            return "0";
        }
        int count = Integer.parseInt(type)-1;
        System.out.println("数据库剩余个数 ："+count);
        SaveInfo info = new SaveInfo();
        info.setId("1112");
        info.setType(count+"");
        int mu = saveInfoMapper.updateByPrimaryid(info);
        System.out.println("mu : "+mu);
        if(mu!=1){
            return "数据库扣除报错";
        }
        System.out.println("mu2 : "+mu);
        return "数据库总数减一";
    }



}