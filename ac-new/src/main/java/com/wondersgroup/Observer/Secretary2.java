//package com.wondersgroup.Observer;
//
//import java.util.LinkedList;
//import java.util.List;
//
//public class Secretary2 implements Subjects {
//
//    //接收通知列表
//    private List<DbServer> dbServers = new LinkedList<>();
//    private String action;
//
//
//    @Override
//    public void add(DbServer observer) {
//        dbServers.add(observer);
//    }
//
//    @Override
//    public void remove(DbServer observer) {
//        dbServers.remove(observer);
//    }
//
//    //通知
//    @Override
//    public void notifyObservers() {
//
//        for (DbServer observerss: dbServers){
//            observerss.update();
//        }
//    }
//
//    @Override
//    public void setAction(String action) {
//
//        this.action = action;
//    }
//
//    @Override
//    public String getAction() {
//        return action;
//    }
//}
