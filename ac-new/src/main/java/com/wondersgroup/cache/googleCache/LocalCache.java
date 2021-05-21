package com.wondersgroup.cache.googleCache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;

import java.util.Map;

public class LocalCache {

    static LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return cacheMap.get(key);
                }
            });
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2; i++) {
            if(cahceBuilder.size()<1){
                test();
            }
            for (String s:cahceBuilder.asMap().keySet()) {
                System.err.println(s+"+++111::"+cahceBuilder.get(s));
            }
        }
        test2();
        for(int i = 0; i < 2; i++){
            for (String s:cahceBuilder.asMap().keySet()) {
                System.err.println(s+"+++222::"+cahceBuilder.get(s));
            }
        }
//        cahceBuilder.invalidateAll();
    }
    private static Map<String,String> cacheMap = Maps.newHashMap();
    public static void test() throws Exception{
        cacheMap.put("test1", "test11");
        cacheMap.put("test2", "test12");
        cacheMap.put("test3", "test13");
        for (String s:cacheMap.keySet()) {
            if(!cahceBuilder.asMap().keySet().contains(s)){
                cahceBuilder.get(s);
            }
        }
    }

    public static void test2() throws Exception{
        cacheMap.put("test5", "test21");
        cacheMap.put("test6", "test22");
        cacheMap.put("test4", "test23");
        for (String s:cacheMap.keySet()) {
            if(!cahceBuilder.asMap().keySet().contains(s)){
                cahceBuilder.get(s);
            }
        }
    }


}
