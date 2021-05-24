package com.infinitePossibilities.Queue.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueues<T> implements Delayed {

    /*到期时刻  */
    private long activeTime;
    /*业务数据，泛型*/
    private T data;

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }


    public DelayQueues(Long activeTime, T data){
        super();
        this.activeTime = activeTime*1000+System.currentTimeMillis();
        this.data = data;
    }


    /**
     * 返回元素到激活时刻的剩余时长
     */
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime
                - System.currentTimeMillis(),unit);
        return d;
    }

    /**按剩余时间排序*/
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.MILLISECONDS)
                -o.getDelay(TimeUnit.MILLISECONDS));
        if (d==0){
            return 0;
        }else{
            if (d<0){
                return -1;
            }else{
                return  1;
            }
        }
    }


}
