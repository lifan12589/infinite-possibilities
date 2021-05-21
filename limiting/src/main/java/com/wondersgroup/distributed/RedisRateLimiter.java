package com.wondersgroup.distributed;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RedisRateLimiter {

    static RedissonClient redisson = null;
    static RRateLimiter myLimiter;

    static {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setConnectionMinimumIdleSize(10);
        //创建Redisson客户端
        redisson = Redisson.create(config);

        /**
         * 基于Redis的分布式限流器可以用来在分布式环境下现在请求方的调用频率。
         * 既适用于不同Redisson实例下的多线程限流，也适用于相同Redisson实例下的多线程限流。
         * 该算法不保证公平性。
         */
        myLimiter = redisson.getRateLimiter("my");

        /**
         * Total rate for all RateLimiter instances
         * 作用在所有的RRateLimiter实例
         * OVERALL
         *
         * Total rate for all RateLimiter instances working with the same Redisson instance
         * 作用在同一个Redisson实例创建的 RRateLimiter上面。
         * PER_CLIENT
         *
         * return : 设置是否成功。 对同一个redis服务端，只需要设置一次。如果redis重启需要重新设置
         */
        boolean bl = myLimiter.trySetRate(RateType.PER_CLIENT, 5, 1, RateIntervalUnit.SECONDS);
    }


    //结合redis，实现分布式的qpi接口限流
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
        //正常退出 status为0时为正常退出程序,也就是结束当前正在运行中的java虚拟机
        System.exit(0);
    }

}
