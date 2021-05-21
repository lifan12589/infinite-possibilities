package com.wondersgroup.redEnvelops;

import java.util.List;

/**
 * @author : msb-zhaoss
 * 父类：红包类
 */
public abstract class RedEnvelops {
    //属性：
    //红包个数：
    int totalCount;
    //红包金额：
    double money;
    //构造器对属性进行赋值：

    public RedEnvelops() {
    }

    public RedEnvelops(int totalCount, double money) {
        this.totalCount = totalCount;
        this.money = money;
    }
    //方法：
    abstract public List<Integer> sendRedEnvelops();
}
