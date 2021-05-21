//package com.wondersgroup.Observer;
//
//public class Client2 {
//
//    public static void main(String[] args) {
//        //通知者
//        Secretary2 secretary = new Secretary2();
//
//        DbService1 db1= new DbService1("db1", secretary);
//        DbService2 db2 = new DbService2("db2", secretary);
//
//        //通知
//        secretary.add(db1);
//        secretary.add(db2);
//
//        //db2 未被通知
//        secretary.remove(db1);
//
//        //
//        secretary.setAction("更新数据库！\n");
//        //发通知
//        secretary.notifyObservers();
//    }
//
//
//
//
//}
