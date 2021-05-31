package com.infinitepossibilities.Test;

import net.spy.memcached.MemcachedClient;
import java.net.InetSocketAddress;
import java.util.concurrent.Future;

public class Demo {

    public static void main(String[] args) throws Exception {
        test();
    }


    public static void test() throws Exception {

        MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));


        //设置缓存   过期时间单位为秒， 0表示永远不过期
        Future future = memcachedClient.set("yiwang", 5, "tongban");

        Object data;

        System.out.println("************* :  "+future.get());//返回状态 true or false
        data= memcachedClient.get("yiwang");
        System.out.println("第一次取出数据："+data);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        data = memcachedClient.get("yiwang");
        System.out.println("第二次取出数据："+data);

        memcachedClient.shutdown();
    }



}
