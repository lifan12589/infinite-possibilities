package com.wondersgroup.lambda.test03;

import java.util.function.Consumer;

/**
 * @author : -
 * 内置四个基础函数式接口
 * 1.消费型接口：
 * Consumer<T> - void accept(T t)
 * 2.供给型接口：
 *  Supplier<T> - T get()
 *  3.函数型结构：
 *  Function<T,R> - R apply(T t)
 *  4.断言型接口  断定型接口
 * Predicate<T> - boolean test(T t)
 */
public class Test {

    public static void bath(double money, Consumer<Double> sm){
        sm.accept(money);
    }

    public static void main(String[] args) {
        /*bath(2990, new Consumer<Double>() {
            public void accept(Double money) {
                System.out.println("传参double型" + money );
            }
        });*/


        bath(2990,x -> System.out.println("传参double型" + x));


//        bath(2000, new Consumer<Double>() {
//            @Override
//            public void accept(Double aDouble) {
//                System.out.println("double型  "+aDouble);
//            }
//        });




    }
}

//消费：
/*interface SpendMoney{
    void buy(double money);
}*/
