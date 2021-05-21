package com.wondersgroup.zookeeper.GWdemo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * 官网示例--读取数据
 */
public class CuratorDemo {

    // 从zookeeper 读取数据，  /runoob节点必须存在
    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework=CuratorFrameworkFactory.
                builder().connectString("127.0.0.1:2181," +
                "127.0.0.1:2182,127.0.0.1:2183").
                sessionTimeoutMs(4000).retryPolicy(new
                ExponentialBackoffRetry(1000,3)).
                namespace("").build();
        curatorFramework.start();
        Stat stat=new Stat();

        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/runoob");
        //有中文，先多试几种编码！
        System.out.println("读取输出 ： "+new String(bytes,"GBK"));
        System.out.println("读取输出 ： "+new String(bytes,"UTF-8"));
        System.out.println("读取输出 ： "+new String(bytes,"GB2312"));
        System.out.println("读取输出 ： "+new String(bytes,"ISO-8859-1"));
        curatorFramework.close();
    }
}
