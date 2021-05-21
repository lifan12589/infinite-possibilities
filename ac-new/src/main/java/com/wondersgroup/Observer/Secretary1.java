package com.wondersgroup.Observer;

import java.util.LinkedList;
import java.util.List;

/*
 *具体通知者
 */
public class Secretary1 implements Subjects {


    //接收通知DB列表
    private List<ObServer> obServers = new LinkedList<>();
    private String action;


    @Override
    public void add(ObServer observer) {
        obServers.add(observer);
    }

    @Override
    public void remove(ObServer observer) {
        obServers.remove(observer);
    }

    //通知
    @Override
    public void notifyObservers() {

        for (ObServer observerss: obServers){
            observerss.update();
        }
    }

    @Override
    public void setAction(String action) {

        this.action = action;
    }

    @Override
    public String getAction() {
        return action;
    }
}
