package com.infinitePossibilities.Queue.BlockingQueue;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.util.concurrent.*;


@Component
@EnableScheduling//可以在启动类上注解也可以在当前文件
public class ArrayBlockingQueues {

    private static ExecutorService service = Executors.newFixedThreadPool(10);
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
//    private static RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();拒绝策略
//    private static ExecutorService executorService = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS, queue, handler);

//    @Scheduled(cron = "0 0/1 * * * ?")//1分钟
    public void query(){
        doSting();
    }

    private static void doSting(){

        new Thread(new Runnable() {
            boolean flag = true;
            @Override
            public void run() {

                System.out.println("开始扫描未推送的办件到BlockingQueue....");
                String type = "0";
                String insertSql = "select * from DANGAN_FJ where DANGAN_TYPE = ? ";
                Object[] insertObject = new Object[] {type};
                RecordSet rs = SQL.execute(insertSql,insertObject);
                while (rs.next()){
                    String ST_FJ_ID = rs.getString("ST_FJ_ID");
                    try {
                        queue.put(ST_FJ_ID);
                    } catch (InterruptedException e) {
                        System.out.println("放入队列出异常...:"+e);
                        e.printStackTrace();
                    }
                }
                System.out.println("BlockingQueue队列里的办件--->："+queue);

                while (flag) {
                    service.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String ST_FJ_ID = queue.take();
                                String updateSql = "update dangan_fj set dangan_type =-1 where st_fj_id = ? ";
                                Object[] updateObject = new Object[]{ST_FJ_ID};
                                RecordSet updateRs = SQL.execute(updateSql, updateObject);
                                int number = updateRs.TOTAL_RECORD_COUNT;
                                //影响行数
                                System.out.println("办件过期更改影响行数:  " + number + "   办件编号为：" + ST_FJ_ID+"  处理线程："+Thread.currentThread().getName());

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    if(queue.isEmpty()){
                        flag =false;
                    }
                }
            }
        },"").start();

    }


    public static void main(String[] args) {
//        Calendar.getInstance().getTimeInMillis();
//        System.out.println(Calendar.getInstance().getTimeInMillis()+1*2000);
//        System.out.println(new Date().getTime());

//        Long sTime =1601452148645L;
//        Long Time = 1601452108676L;
//        System.out.println(sTime-Time);

        String type = "-1";
        String insertSql = "select count(*) from DANGAN_FJ where DANGAN_TYPE = ? ";
        Object[] insertObject = new Object[] {type};
        RecordSet rs = SQL.execute(insertSql,insertObject);
        System.out.println(rs);
        int i = rs.getRow();
        double ii = rs.getNumber(1);
        System.out.println(i);
        System.out.println(ii);


    }





}
