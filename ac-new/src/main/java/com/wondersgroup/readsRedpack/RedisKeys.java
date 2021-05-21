package com.wondersgroup.readsRedpack;

import static java.lang.Thread.sleep;

public class RedisKeys {

    /**
     * 获取HB池子的key
     * @param orderId
     * @return
     */
    public static String getHbPoolKey(Long orderId){

        return "hb:pool:" + orderId;
    }

    /**
     * 获取HB领取用户记录key
     * @param orderId
     * @return  领过
     */
    public static String getHbRdKey (Long orderId){

        return "hb:rd:" + orderId;
    }

    /**
     * HB与用户信息列表key
     *                           展示 获取到的 用户和HB
     * @param orderId
     * @return
     */
    public static String getDetailListKey(Long orderId){
        return "hb:detailList:" + orderId;
    }
}