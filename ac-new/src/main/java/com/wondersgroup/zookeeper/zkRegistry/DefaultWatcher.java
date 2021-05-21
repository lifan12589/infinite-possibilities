package com.wondersgroup.zookeeper.zkRegistry;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class DefaultWatcher implements Watcher {

    private CountDownLatch countDownLatch;

    public void setCountDownLatch(CountDownLatch countDownLatch){

        this.countDownLatch = countDownLatch;
    }



    @Override
    public void process(WatchedEvent watchedEvent) {

        switch (watchedEvent.getState()) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                countDownLatch.countDown();
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
        }
        System.out.println(watchedEvent.toString());

    }

}
