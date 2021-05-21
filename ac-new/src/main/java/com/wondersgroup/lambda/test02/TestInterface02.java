package com.wondersgroup.lambda.test02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : -
 * 语法结构02：有一个参数，无返回值：
 */
public interface TestInterface02 {
    void testMethod(int num);
}

class Demo01{
    //这是main方法，程序的入口
    public static void main(String[] args) {


        TestInterface02 ttt = new TestInterface02() {
            @Override
            public void testMethod(int x) {
                System.out.println("这个方法的参数为：" + x);
            }
        };
        ttt.testMethod(30);


        TestInterface02 tt = (x) -> System.out.println("这个方法的参数为：" + x);
        tt.testMethod(20);


        TestInterface02 t = x -> System.out.println("这个方法的参数为：" + x);
        t.testMethod(10);


        /*
        注意：
        1.参数名字随便起
        2.参数的类型可以省略 - 》上下文有类型推断
        3.参数只有一个的话，()可以省略
         */

    }
}
