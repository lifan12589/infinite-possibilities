package com.wondersgroup.redEnvelops;

import java.util.*;

/**
 * @author : msb-zhaoss
 * 子类：拼手气红包： 继承父类
 */
public class SpellLuckRedEnvelops extends RedEnvelops{
    public SpellLuckRedEnvelops(){
        super();
    }

    public SpellLuckRedEnvelops(int totalCount, double money) {
        super(totalCount, money);
    }

    @Override
    public List<Integer> sendRedEnvelops() {
        //一定有一个集合，来存放分好的红包：
        List<Integer> list = new ArrayList<>();
        /*
        红包的分配原则：5个红包  ， 20元：
        第一个人： 0.2
        第二个人： 2.4
        第三个人：  最低 ： 0.01 - 最多  ： 剩余金额 - 剩余人数*0.01
         */
        //将红包金额转换为 分： 1元 = 100分
        int smallMoney = (int)(super.money*100);
        //定义初始的红包的余额：
        int balance = smallMoney;
        //红包红包剩余数量：
        int count = super.totalCount;

        //分红包：
        for (int i = 1; i <= super.totalCount ; i++) {
            int amount = 0;
            //for循环内部就是每个红包的逻辑：
            if(i != super.totalCount){//如果你不是最后一个红包：
                //红包  随机数生成当前 红包的金额 ：金额在 1 -   剩余金额 - 剩余人数*1
                Random r = new Random();
                amount = r.nextInt(balance - (super.totalCount - i)*1) + 1; //[0,5) +1 =  [1,5]
            }else{//如果你是最后一个红包，你就分剩余的所有钱
                amount = balance;
            }
            //将分的钱放入list集合中：
            list.add(amount);
            //将amount这个钱在余额中减掉：
            balance = balance - amount;
        }

        return list;
    }
}
