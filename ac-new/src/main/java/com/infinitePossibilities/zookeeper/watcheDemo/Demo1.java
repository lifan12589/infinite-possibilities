package com.infinitePossibilities.zookeeper.watcheDemo;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

/**
 * 分布式配置中心demo1
 * 简单理解
 * @author
 *
 */
@Component
public class Demo1 {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
//        @PostConstruct
//        public static void init() throws Exception{
        //zookeeper配置数据存放路径
        String path = "/watcher";
        //连接zookeeper并且注册一个默认的监听器
        zk = new ZooKeeper("127.0.0.1:2181", Integer.MAX_VALUE, new Watcher() {

                    @Override
                    public void process(WatchedEvent event) {
                        System.out.println("事件类型为:" + event.getType());
                        System.out.println("事件发生的路径:" + event.getPath());
                        System.out.println("通知状态为:" +event.getState());

                        if (KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
                            if (EventType.None == event.getType() && null == event.getPath()) {
                                connectedSemaphore.countDown();
                            } else if (event.getType() == EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                                try {
                                    System.out.println("配置已修改，新值为--->：" + new String(zk.getData(event.getPath(), true, stat)));
                                } catch (Exception e) {
                                }
                            }
                        }
                    }
                });
        //等待zk连接成功的通知
        connectedSemaphore.await();

        //没有 /watcher 则创建 并赋值为0
        if(zk.exists(path,false)==null) {
            zk.create("/watcher", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 取出子目录节点列表
        System.out.println("子目录节点列表--->"+zk.getChildren(path,true));

        //获取path目录节点的配置数据，并注册默认的监听器
        System.out.println("path目录的值--->:"+new String(zk.getData(path, true, stat)));

        Thread.sleep(Integer.MAX_VALUE);
    }


}