package com.infinitePossibilities.lambda.test02;

/**
 * @author : -
 * 语法结构1：无参，无返回值
 */
public interface TestInterface {
    void testMethod();
}

class Test01{
    //这是main方法，程序的入口
    public static void main(String[] args) {

        TestInterface ttt = new TestInterface() {
            @Override
            public void testMethod() {
                System.out.println("ttt");
            }
        };
        ttt.testMethod();


        TestInterface tt = () -> {
            System.out.println("tt");
        };
        tt.testMethod();


        TestInterface t = () -> System.out.println("t       这是一个无参的，无返回值的方法");
        t.testMethod();

        /*
        注意1：如果方法体中只有一句话的时候，{}可以省略不写
         */

    }
}
