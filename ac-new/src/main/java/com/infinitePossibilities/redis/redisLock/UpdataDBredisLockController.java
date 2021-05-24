package com.infinitePossibilities.redis.redisLock;

import com.infinitePossibilities.redis.RedisPoolUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UpdataDBredisLockController {

    private static RedisPoolUtils redisPoolUtils = new RedisPoolUtils();



    @RequestMapping("/dbLock.do")
    public void getKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        String Key = "keys";
        String redisKey = Key;
        String lockKey = "locks:"+Key;
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
                    String st_fj_id = Key;
                    String updateSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
                    Object[] updateObject = new Object[] {count,st_fj_id};
                    RecordSet updateRs  = SQL.execute(updateSql,updateObject);
                    int number = updateRs.TOTAL_RECORD_COUNT;
                    //影响行数
                    System.out.println("数据库影响行数:"+number);

                    if(number==1){
                        String counts = "";
                        String selectSql = "select * from dangan_fj where st_fj_id = ?";
                        Object[] selectObject = new Object[]{Key};
                        RecordSet selectRs = SQL.execute(selectSql, selectObject);
                        while (selectRs.next()) {
                            counts = selectRs.getString("COUNT");
                        }
                        System.out.println("数据库剩余---->"+counts);
                        String redisRs = jedis.setex(redisKey,60*60,counts);
                        System.out.println("更改redis结果--->:"+redisRs);
                        response.getWriter().print(Thread.currentThread().getName().toString() + "去数据库拿结果~~~~:" + counts.toString());
                        return;
                    }else {
                        System.out.println(Thread.currentThread().getName() + "更改数据库失败~~~~");
                        response.getWriter().print(Thread.currentThread().getName() + "更改数据库失败~~~~");
                        return ;
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
        response.getWriter().print( Thread.currentThread().getName()+"最后结果lockRequestId--->"+lockRequestId);

    }



    @RequestMapping("/redislock.do")
    public void getRedisKey(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");
        String Key = "keys";
        String redisKey = Key;
        String lockKey = "locks:"+Key;
        String lockRequestId = UUID.randomUUID().toString();
        System.out.println(Thread.currentThread().getName() + "lockRequestId--->"+lockRequestId);
        Jedis jedis = redisPoolUtils.getResource();

        try {
            while (true) {
                //获取分布式锁，时间设为3秒
                boolean getLock = JedisSuoUtils.tryGetDistributedLock(jedis, lockKey, lockRequestId, 3000);
                System.out.println(Thread.currentThread().getName() + "获取锁的结果：--->" + getLock);
                if (getLock) {
                    String count = jedis.get(redisKey);
                    System.out.println("redis缓存为---->"+count);
                    int con = Integer.parseInt(count);
                    if(count!=null&&con!=0){
                        String counts = con-1+"";
                        String redisRs = jedis.setex(redisKey,60*60,counts);
                        System.out.println("更改redis结果--->:"+redisRs);
                        response.getWriter().print(Thread.currentThread().getName().toString() + "更改数据为~~~~:" + counts.toString());
                        return;
                    }else {
                        System.out.println(Thread.currentThread().getName() + "更改数据失败~~~~");
                        response.getWriter().print(Thread.currentThread().getName() + "更改数据失败~~~~");
                        return ;
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
        response.getWriter().print( Thread.currentThread().getName()+"最后结果lockRequestId--->"+lockRequestId);

    }







}

