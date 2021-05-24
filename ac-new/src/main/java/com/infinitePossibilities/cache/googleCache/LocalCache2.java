package com.infinitePossibilities.cache.googleCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class LocalCache2 {

    private static LoadingCache<String, Object> localCache = CacheBuilder.newBuilder()
            .initialCapacity(100)
            .maximumSize(10000)
            //指定时间内没有被创建覆盖，则指定时间过后，再次访问时，会去刷新该缓存，在新值没有到来之前，始终返回旧值
            .refreshAfterWrite(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, Object>() {
                @Override
                public String load(String s) {
                    return "null";
                }
            });

    /**
     * 获得缓存
     *
     * @param key   key
     * @param value value
     */
    public static void setKey(String key, Object value) {
        System.out.println("==> Cache.setKey [key:{} value:{}]"+ key+"----"+ value);
        localCache.put(key, value);
        System.out.println("localCache : "+localCache);

        for (String s:localCache.asMap().keySet()) {
            try {
                System.err.println(s+"+++111::"+localCache.get(s));
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 设置缓存
     *
     * @param key key
     * @return Object
     */
    public static Object getKey(String key) {
        try {
            Object value = localCache.get(key);
            if ("null".equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }




}
