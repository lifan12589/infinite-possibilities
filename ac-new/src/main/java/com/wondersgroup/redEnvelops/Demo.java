package com.wondersgroup.redEnvelops;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author : msb-zhaoss
 */
public class Demo {
    //这是main方法，程序的入口
    public static void main(String[] args) {
        //加入扫描器，从键盘录入：
        Scanner sc = new Scanner(System.in);
        System.out.println("请问你要发几个红包：");
        int count = sc.nextInt();
        System.out.println("请问你要发多少钱：");
        int money = sc.nextInt();
        //你的红包是在微信群中发的，红包必须分给对应的人：---》人 ---》多一个人类
        //微信群中的人都是你的红包接收的群体：
        //搞一个微信群：把人装到一个集合中：
        List<Person> list = new ArrayList<>();
        list.add(new Person("法老"));
        list.add(new Person("踽踽独行"));
        list.add(new Person("叶"));
        list.add(new Person("浮沙"));
        list.add(new Person("稳"));
        list.add(new Person("螺丝钉"));

        //在微信群发红包：
        SpellLuckRedEnvelops s = new SpellLuckRedEnvelops(count,money);
        List<Integer> moneylist = s.sendRedEnvelops();

        //给随机的人  分配  随机的红包
        Random r = new Random();
        for (int i = moneylist.size(); i > 0 ; i--) {
            //随机的成员的下标：
            int m = r.nextInt(list.size());
            //随机的红包的下标：
            int n = r.nextInt(moneylist.size());
            //获取对应的人员：
            Person p = list.get(m);
            //给对应的人随机分一个红包：
            p.shareMoney = moneylist.get(n);
            //分完钱之后呢？分完钱以后这个人要从集合中移除，这个红包的金额也要从红包中移除：
            list.remove(m);
            moneylist.remove(n);
            //展示群员 抢红包的信息：
            System.out.println(p.name+"抢到了"+p.shareMoney/100.0+"元");

        }

        //提示  剩下的人没有抢到红包的，显示 手速慢：
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).name + ",手慢了，红包派完了");
        }




    }
}
