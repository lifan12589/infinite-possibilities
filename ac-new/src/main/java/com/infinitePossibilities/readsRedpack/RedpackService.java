package com.infinitePossibilities.readsRedpack;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

public class RedpackService {


    private JedisUtils jedisUtils;

    public RedpackService(JedisUtils jedisUtils) {
        this.jedisUtils = jedisUtils;
    }

    /**
     * 生成HB
     *
     * @param orderId
     */
    public void genRedpack(long orderId, int redPackCount) {
        Boolean exists = jedisUtils.exists(RedisKeys.getHbPoolKey(orderId));
        if (!exists) {
            //根据业务规则生成HB
            int totalAmount = 2000;//总的HB金额20 也就是2000分
            int[] redpacks = doPartitionRedpack(totalAmount, redPackCount);

            String[] list = new String[redpacks.length];
            //将生成的HBpush到redis中
            for (int i = 0; i < redpacks.length; i++) {
                JSONObject object = new JSONObject();
                object.put("hbId", i + 1); //HBID
                object.put("amount", redpacks[i]);   //HB金额,存的是分
                list[i] = object.toJSONString();
            }
            jedisUtils.lpush(RedisKeys.getHbPoolKey(orderId), list);
        }
    }

    /**
     * 划分HB
     *
     * @param totalAmount  HB总额 单位：分
     * @param redPackCount HB数量
     * @return
     */
    private int[] doPartitionRedpack(int totalAmount, int redPackCount) {
        Random random = new Random();
        int randomMax = totalAmount - redPackCount;//每个人至少分1分钱，2000 - 7= 1993分 也就是要随机分的钱。
        //要把1993 随机分成7份，我们需要向1993 这个数字中插入7个点
        // 比如 7 93 100  100 100 500  500  600 这8个数字把1993分成了8份：7 93 100  100 100 500  500  600
        int[] posArray = new int[redPackCount - 1];
        for (int i = 0; i < posArray.length; i++) {
            int pos = random.nextInt(randomMax);
            posArray[i] = pos;
        }
        Arrays.sort(posArray);//对数组进行排序    打乱后排序展示随机性
        //生成红包
        int[] redpacks = new int[redPackCount];
        for (int i = 0; i <= posArray.length; i++) {
            if (i == 0) {
                redpacks[i] = posArray[i] + 1;//第一份  前三份HB  单独 从 propertis 中取出
            } else if (i == posArray.length) {//如果循环到posArray.length，
                // 此时数组已越界1位，randomMax - 该值 + 1分钱=最后一份
                redpacks[i] = randomMax - posArray[i - 1] + 1;
            } else {
                redpacks[i] = posArray[i] - posArray[i - 1] + 1;
            }
        }
        return redpacks;
    }


    /**
     * 抢HB
     *
     * @param userId
     * @param orderId
     */
    public String snatchRedpack(long userId, long orderId) {
        Object object = jedisUtils.eval(LuaScript.getHbLua, 4,
                RedisKeys.getHbPoolKey(orderId),//
                RedisKeys.getDetailListKey(orderId),//
                RedisKeys.getHbRdKey(orderId), String.valueOf(userId));

        return (String) object;
    }
}
