package com.wondersgroup.utils.threads;

public class ProdCons2 {

    public static void main(String[] args) {

        Goods goods = new Goods();

        Producer producer = new Producer(goods);
        Consumer consumer = new Consumer(goods);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }


     public static class Producer implements Runnable {

        private Goods goods;

        public Producer(Goods goods) {
            this.goods = goods;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                if (i % 2 == 0) {
                    goods.set("产品","1");
                } else {
                    goods.set("产品","2");
                }
            }
        }
    }


    public static class Consumer implements Runnable {

        private Goods goods;

        public Consumer(Goods goods) {
            this.goods = goods;
        }

        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                goods.get();
            }
        }
    }


   static class Goods {

    private String brand;
    private String name;
    // 默认是不存在商品的，如果值等于true的话，代表有商品
    private boolean flag = false;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 消费者获取商品
    public synchronized void get() {
        /*
         * 如果flag等于false的话，意味着生产者没有生产商品，此时消费者无法消费，需要让消费者线程进入到阻塞状态，等待生产者生产，当 有商品之后，再开始消费
         */
//        System.out.println("消费者开始消费");
        if (!flag) {
            try {
                System.out.println("没有商品 , 消费者开始进入wait状态，并释放锁");
                wait();
                System.out.println("消费者被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("消费者取走了" + this.getBrand() + "----" + this.getName());
        flag = false;
        // 唤醒生产者去进行生产
        System.out.println("消费者 --> 唤醒生产者");
        notify();
    }

    // 生产者生产商品
    public synchronized void set(String brand, String name) {
        // 当生产者抢占到cpu资源之后会判断当前对象是否有值，如果有的话，以为着消费者还没有消费，需要提醒消费者消费，同时
        // 当前线程进入阻塞状态，等待消费者取走商品之后，再次生产，如果没有的话，不需要等待，不需要进入阻塞状态，直接生产即可
//        System.out.println("生产者开始生产");
        if (flag) {
            try {
                System.out.println("有商品 , 生产者开始进入wait状态，并释放锁");
                wait();
                System.out.println("生产者被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setBrand(brand);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setName(name);
        System.out.println("生产者生产了" + this.getBrand() + "--" + this.getName());
        // 如果代码执行到此处，意味着已经生产完成，需要将flag设置为true
        flag = true;
        // 唤醒消费者去进行消费
        System.out.println("生产者 --> 唤醒消费者");
        notify();
    }
}




}