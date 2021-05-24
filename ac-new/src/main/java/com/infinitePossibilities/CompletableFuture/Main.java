package com.infinitePossibilities.CompletableFuture;

import com.infinitePossibilities.utils.requestUtils.HttpApiClient;
import java.util.concurrent.CountDownLatch;

public class Main {

    private static final int Thread_num = 2000;
    private static final CountDownLatch cd = new CountDownLatch(Thread_num);


    public static void cdSendData() throws InterruptedException {
        for (int i=0;i<Thread_num;i++){
            Thread t = new Thread(()->{
                try {
                    cd.countDown();
                    cd.await();
                    String url = "http://localhost:8080/comple.do";
                    String serialNo = HttpApiClient.getData2(url, "" );
                    System.out.println("serialNo:   "+serialNo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        Thread.sleep(5000);
    }



    public static void main(String[] args) throws InterruptedException {
        cdSendData();

    }




}
