package com.wondersgroup.Observer;

/*
 *具体观察者 db1
 */
public class DbService1 extends ObServer {


    public DbService1(String name, Subjects subject) {
        super(name, subject);
    }


    @Override
    public void update() {
        System.out.println(subject.getAction() + name + "做了更新....");

    }
}
