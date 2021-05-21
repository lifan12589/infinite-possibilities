package com.infinitePossibilities.cache.localCache;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /**
         *LFU：Least Frequently Used
         *从frequently可以看出这个策略是比较访问频率的。也就是在一定的时间内谁的访问次数最少将会被淘汰，这是一种比较合理的策略；
         */
        LFUCache localCache = new LFUCache(3);
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            localCache.put(i+"", "节点-"+i,40000);
        }
        localCache.put("3","单独节点",40000);

        for (int i = 0; i < 4; i++) {
            System.out.println(localCache.get(""+i));
        }



        /**
         * LRU：Least Recently Used
         * 这个是比较最近的访问时间的。也就是在一定时间内，谁最早被访问将会被淘汰。可以看成是LFU的简化。
         */
        LRUCache lruCache = new LRUCache(5);
        for(int i = 0;i< 5;i++) {
            lruCache.put("Node" + i, "节点-" + i);
        }
        lruCache.put("Node5","单独节点");

        for(int i = 0; i< 6;i++) {
            System.out.println(lruCache.get("Node" + i));
        }



    }













}

