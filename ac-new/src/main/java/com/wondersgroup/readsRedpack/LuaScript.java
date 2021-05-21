package com.wondersgroup.readsRedpack;

public interface LuaScript {

    /**
     * 脚本调用方式
     * Object object = jedisUtils.eval(LuaScript.getHbLua,//lua脚本
     * 4,//参数个数
     *  RedisKeys.getHbPoolKey(orderId),//对应脚本里的KEYS[1]
     *  RedisKeys.getDetailListKey(orderId),//对应脚本里的KEYS[2]
     *  RedisKeys.getHbRdKey(orderId),//对应脚本里的KEYS[3]
     *  String.valueOf(userId));//对应脚本里的KEYS[4]
     *  CAS
     *  抢HB进来的用户顺序列队  前10
     *  单redis 链表
     *  记录用户 定时释放 1272880650749648896  事务 回退
     *
     *
     *
     */
    //限制 一人一个  没有 不进入下一步
    public static String getHbLua =
            //查询用户是否已抢过HB，如果用户已抢过HB，则直接返回nil 
            "if redis.call('hexists', KEYS[3], KEYS[4]) ~= 0 then\n"   +
                    //如果抢过HB 返回“1”  1 已经有了
                    "return '1';\n" +
                    "else\n"  +
                    //从优惠券池取出一个小红包
                    "local hb = redis.call('rpop', KEYS[1]);\n"  +
                    //判断HB池的HB是否为不空
                    "if hb then\n"  +
                    "local x = cjson.decode(hb);\n"  +
                    //将HB信息与用户ID信息绑定，表示该用户已抢到HB
                    "x['userId'] = KEYS[4];\n"  +
                    "local re = cjson.encode(x);\n"  +
                    //记录用户已抢过 比如 hset hb:rd:{orderId}  {userId}  1
                    "redis.call('hset', KEYS[3], KEYS[4], '1');\n"  +
                    //将抢HB的结果详情存入hb:detailList:{orderId}
                    "redis.call('lpush', KEYS[2], re);\n" +
                    "return re;\n"  +
                    "else\n"  +
                    //如果HB已被抢完 返回“0”  0没有有HB
                    "return '0';" +
                    "end\n"  +
                    "end\n"  +
                    "return nil";
}
