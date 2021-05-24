package com.infinitePossibilities.zookeeper.zkLock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 *Curator也可以实现
 * https://my.oschina.net/cloudcoder/blog/365484
 * https://www.csdn.net/gather_2c/MtTaEg0sMzg3MjMtYmxvZwO0O0OO0O0O.html
 *https://blog.csdn.net/zh15732621679/article/details/81436739?utm_medium=distribute.pc_aggpage_search_result.none-task-blog-2~all~baidu_landing_v2~default-10-81436739.nonecase&utm_term=%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81&spm=1000.2123.3001.4430
 *
 *
 */
@Controller
public class ZookeeperLockController {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static Stat stat = new Stat();
    private ZooKeeper zooKeeper;

    private Cache cache;

    @Resource
    private CacheManager cacheManager;
    @PostConstruct
    public void init() {
        this.cache = cacheManager.getCache("jsonCache");
    }
    @RequestMapping("/zookeeperLock.do")
    public void getKey(HttpServletRequest request, HttpServletResponse response) throws Exception {

        zooKeeper = new ZooKeeper("localhost",10000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected == event.getState()) {
                    connectedSemaphore.countDown();
                    System.out.println("Zookeeper连接已建立~~~~~~~");
                }
            }
        });

        Lock lock = new ZookeeperLock("/zkLock","lockKey",zooKeeper);
        try {
            if (lock.tryLock(10000, TimeUnit.MILLISECONDS)) {//设定10秒获取锁时间
                System.out.println(Thread.currentThread().getName() + "   获得锁执行业务");

//                Stat statS = zooKeeper.exists("/dbOver", false);
//                    if(statS!=null){
//                        System.out.println(Thread.currentThread().getName() + "   数据库更改完成~~~~~");
//                        return;
//                }

//                String data = new String(zooKeeper.getData("/zkLock", false, null));
//                if(data.equals("end")){
//                    System.out.println(Thread.currentThread().getName() + "   数据库更改完成~~~~~");
//                    return;
//                }
                if (this.cache.get("zkLock") != null&& this.cache.get("zkLock").get().equals("OK")) {
                    System.out.println(Thread.currentThread().getName() + "   数据库更改完成~~~~~");
                    return;
                }

                int count = 1;
                String st_fj_id = "keys";
                String updateSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
                Object[] updateObject = new Object[]{count, st_fj_id};
                RecordSet updateRs = SQL.execute(updateSql, updateObject);
                int number = updateRs.TOTAL_RECORD_COUNT;
                //影响行数
                System.out.println("数据库影响行数:" + number);
                if(number==0){
                    System.out.println(Thread.currentThread().getName() + "  去打标志~~~~~");
//                    zooKeeper.create("/dbOver", new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//                    zooKeeper.setData("/zkLock","end".getBytes(),-1);
                    this.cache.put("zkLock","OK");

                }
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
