package com.infinitePossibilities.redis.redisLock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.infinitePossibilities.redis.RedisPoolUtils;
import redis.clients.jedis.Jedis;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

    private static RedisPoolUtils redisPoolUtils = new RedisPoolUtils();
    private final static String  key = "key";
    private static final int  Thread_num   = 8;
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
                    String res = getKey("31000000");
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


    public static String getKey(String sheng) {

//synchronized (sheng){

        String redisKey = sheng;
        String lockKey = "locks:"+sheng;
        String lockRequestId = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName() + "lockRequestId--->"+lockRequestId);
        Jedis jedis = redisPoolUtils.getResource();

        try {

            while (true) {
                JSONArray jsonArray = new JSONArray();
//            String array = redisPoolUtils.get(sheng);
//            if(array!=null){
//                System.out.println(Thread.currentThread().getName()+"删除redis数据");
//                redisPoolUtils.del(sheng);
//            }
                //获取分布式锁，时间设为1秒
                boolean getLock = JedisSuoUtils.tryGetDistributedLock(jedis, lockKey, lockRequestId, 5000);
                System.out.println(Thread.currentThread().getName() + "获取锁的结果：--》" + getLock);
                if (getLock) {


                    String sql = "select * from DANGAN_SHI_QU where SHI_ID = ?";
                    Object[] objects = new Object[]{sheng};
                    RecordSet rs = SQL.execute(sql, objects);
                    while (rs.next()) {
                        Long timestamp = System.currentTimeMillis();  //获取当前时间戳
                        JSONObject json = new JSONObject();
                        String code = rs.getString("NAME_ID");
                        String name = rs.getString("NAME");
                        json.put("code", code);
                        json.put("name", name);
                        json.put("time", timestamp);
                        jsonArray.add(json);
                    }
//                System.out.println(Thread.currentThread().getName()+"去数据库拿结果~~~~");
//                    redisPoolUtils.setex(redisKey, jsonArray.toString(), 60 * 60);
                    jedis.setex(redisKey,60*60,jsonArray.toString());
                    return Thread.currentThread().getName() + "去数据库拿结果~~~~" + jsonArray.toString();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //不论结果如何，一定要释放锁
            boolean releaseLock = JedisSuoUtils.releaseDistributedLock(jedis, lockKey, lockRequestId);
            jedis.close();
            System.out.println(Thread.currentThread().getName()+"释放锁的结果：--》"+releaseLock);
        }
       return Thread.currentThread().getName()+"最后结果lockRequestId--->"+lockRequestId;
    }

    }
//}
