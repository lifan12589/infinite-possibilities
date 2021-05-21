package com.infinitePossibilities.Observer;

/**
 * 通知者接口
 */
public interface Subjects {

    //增加
    public void add(ObServer observer);
    //删除
    public void remove(ObServer observer);
    //通知
    public void notifyObservers();

     //状态
    public void setAction(String action);

    public String getAction();
}
