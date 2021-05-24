package com.infinitePossibilities.Observer;

/*
 *具体观察者 db2
 */
public class DbService2 extends ObServer {

    public DbService2(String name, Subjects subject) {
        super(name, subject);
    }

    @Override
    public void update() {
        System.out.println(subject.getAction() + name + "做了更新....");

    }
}
