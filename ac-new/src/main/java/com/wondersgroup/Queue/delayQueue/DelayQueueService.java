package com.wondersgroup.Queue.delayQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.DelayQueue;

@Service
public class DelayQueueService {

    @Autowired
    private ProcessToId processToId;
    /*负责保存限时推送办件的队列*/
    private static DelayQueue<DelayQueues<String>> delay
            = new DelayQueue<DelayQueues<String>>();


    /*任务放入延迟队列*/
    public void Delay(String ST_FJ_ID,Long expireTime){
        DelayQueues<String> delayQueues = new DelayQueues<String>(expireTime,ST_FJ_ID);
        delay.put(delayQueues);
        System.out.println("[办件超时时长："+expireTime+"秒]被推入本地检查队列，办件编号：" +ST_FJ_ID);
    }

    private class TaskSend implements Runnable{

        private ProcessToId processToId;

        public TaskSend(ProcessToId processToId){
            super();
            this.processToId = processToId;
        }

        @Override
        public void run() {

            System.out.println("启动 处理未推送数据线程......");
            while (!Thread.currentThread().isInterrupted()){
                try {
                    DelayQueues<String> delayQueues = delay.take();
                    if(delayQueues!=null){
                        processToId.doProcess(delayQueues.getData());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("关闭 处理未推送数据线程......");
        }
    }

    /*处理到期办件的线程*/
    private Thread thread;

//    @PostConstruct
    public void init(){
        thread = new Thread(new TaskSend(processToId));
        thread.start();
    }

//    @PreDestroy
    public void close(){
        thread.interrupt();
    }


}
