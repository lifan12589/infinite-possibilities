package com.infinitePossibilities.redEnvelops;

import java.util.List;

/**
 * @author : msb-zhaoss
 */
public class Test {
    //这是main方法，程序的入口
    public static void main(String[] args) {
        //调用有参构造器，不能调用空参：
        SpellLuckRedEnvelops s = new SpellLuckRedEnvelops(3,10);
        List<Integer> list = s.sendRedEnvelops();
        //遍历list：
        for(Integer i: list){
            System.out.println(i);
        }
    }
}
