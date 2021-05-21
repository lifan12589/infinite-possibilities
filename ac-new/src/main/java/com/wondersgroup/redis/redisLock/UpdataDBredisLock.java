package com.wondersgroup.redis.redisLock;

import com.wondersgroup.redis.RedisPoolUtils;
import com.wondersgroup.redis.redisLock.JedisSuoUtils;
import redis.clients.jedis.Jedis;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class UpdataDBredisLock {

    private static RedisPoolUtils redisPoolUtils = new RedisPoolUtils();
    private final static String  key = "keys";
    private static final int  Thread_num   = 20;
    private static final CyclicBarrier cb = new CyclicBarrier(Thread_num);

    public static void sendThreads() throws InterruptedException {
        Thread [] threads = new Thread[Thread_num];
        for(int i=0;i<Thread_num;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    String res = getKey(key);
                    System.out.println(res);

                }
            });
            threads [i] = thread;
            thread.start();
        }
        for(Thread thread : threads){
            thread.join();
        }
        System.out.println("执行结束======");


    }

    public static void main(String[] args) throws InterruptedException {
        sendThreads();

    }


    public static String getKey(String key) {



        String redisKey = key;
        String lockKey = "locks:"+key;
        String lockRequestId = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName() + "lockRequestId--->"+lockRequestId);
        Jedis jedis = redisPoolUtils.getResource();

        try {

            while (true) {

                //获取分布式锁，时间设为3秒
                boolean getLock = JedisSuoUtils.tryGetDistributedLock(jedis, lockKey, lockRequestId, 3000);
                System.out.println(Thread.currentThread().getName() + "获取锁的结果：--->" + getLock);
                if (getLock) {

                    int count = 1;
                    String st_fj_id = key;
                    String updateSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
                    Object[] updateObject = new Object[] {count,st_fj_id};
                    RecordSet updateRs  = SQL.execute(updateSql,updateObject);
                    int number = updateRs.TOTAL_RECORD_COUNT;
                    //影响行数
                    System.out.println("数据库影响行数:"+number);

                    if(number==1){
                        String counts = "";
                        String selectSql = "select * from dangan_fj where st_fj_id = ?";
                        Object[] selectObject = new Object[]{key};
                        RecordSet selectRs = SQL.execute(selectSql, selectObject);
                        while (selectRs.next()) {
                            counts = selectRs.getString("COUNT");
                        }
                        System.out.println("数据库剩余---->"+counts);
                        String redisRs = jedis.setex(redisKey,60*60,counts);
                        System.out.println("更改redis结果--->:"+redisRs);
                        return Thread.currentThread().getName() + "去数据库拿结果~~~~:" + counts;
                    }else {
                        return Thread.currentThread().getName() + "更改数据库失败~~~~";
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不论结果如何，一定要释放锁
            boolean releaseLock = JedisSuoUtils.releaseDistributedLock(jedis, lockKey, lockRequestId);
            jedis.close();
            System.out.println(Thread.currentThread().getName()+"释放锁的结果：--->"+releaseLock);
        }
        return Thread.currentThread().getName()+"最后结果lockRequestId--->"+lockRequestId;
    }

}

