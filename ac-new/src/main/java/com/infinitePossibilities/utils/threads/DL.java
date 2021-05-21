package com.infinitePossibilities.utils.threads;


public class DL {

    //volatile量两大作用： 1.保证线程间可见性 2.禁止 指令重排序

    static volatile DL instance;//使用volatile 禁止 指令重排序

    public static Object getSingle(){
        if(instance==null){
            synchronized (DL.class){//锁类而不是锁对象
                if(instance==null){
                    instance=new DL();

                }
            }
        }
        return  instance;
    }
    public static void main(String[] args) {
        System.out.println(getSingle());//测试1
        System.out.println(getSingle());//测试2
        System.out.println(getSingle());//测试3
    }

    /**
     * 分析:
     * 加volatile的必要性
     * 原因在于指令重排的存在，加入volatile可以禁止指令重排
     *
     * 当某一个线程执行到第一次检测，读取到的instance不为null时，instance的引用对象可能没有完成初始化
     * instance = new DL();分为以下三步完成（伪代码）
     *
     * memory = allocate();   1、初始化对象内存空间
     * instance(memory);      2、初始化对象
     * instance = memory; 3、设置instance指向实例好的对象，此时instance!=null
     *
     * 由于步骤2 3 不存在数据依赖关系，如果发生指令重排
     * memory = allocate();   1、初始化对象内存空间
     * instance = memory; 3、设置instance指向实例好的对象，此时instance!=null,但是对象还没有初始化完成
     * inStance(memory);      2、初始化对象
     *
     * 所以当一条线程访问instance不为null时，由于instance实例未必已初始化完成，也就造成了线程安全问题
     */
}
