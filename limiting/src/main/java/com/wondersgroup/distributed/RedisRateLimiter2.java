package com.wondersgroup.distributed;

import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RedisRateLimiter2 {

    static RedissonClient redisson = null;
    static RRateLimiter myLimiter;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setConnectionMinimumIdleSize(10);

        //创建Redisson客户端
        redisson = Redisson.create(config);
        myLimiter = redisson.getRateLimiter("my2");
        boolean bl = myLimiter.trySetRate(RateType.OVERALL, 5, 1, RateIntervalUnit.SECONDS);
    }

    public static void test() {

        CountDownLatch cd = new CountDownLatch(1);
        Random rd = new Random();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {

                try {
                    cd.await(); //使得当前线程阻塞
                    Thread.sleep(rd.nextInt(1000)); //模拟20个请求的并发，有一点点先后顺序的差异
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    //获取令牌
                    System.out.println(Thread.currentThread().getName() + " : " + myLimiter.tryAcquire());
                }

            }).start();
        }

        cd.countDown();
    }

    public static void main(String[] args) {

        test();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

}
