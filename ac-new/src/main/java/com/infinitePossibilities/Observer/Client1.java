package com.infinitePossibilities.Observer;

public class Client1 {

    public static void main(String[] args) {
        //通知者
        Secretary1 secretary = new Secretary1();

        DbService1 db1= new DbService1("db1", secretary);
        DbService2 db2 = new DbService2("db2", secretary);

        //加入通知列表
        secretary.add(db1);
        secretary.add(db2);

        //db2 未被通知 ->db2移除列表
        secretary.remove(db2);


        secretary.setAction("更新数据库！\n");
        //发通知
        secretary.notifyObservers();
    }




}
