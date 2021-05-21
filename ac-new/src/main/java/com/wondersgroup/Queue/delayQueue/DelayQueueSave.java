package com.wondersgroup.Queue.delayQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DelayQueueSave {

    @Autowired
    private DelayQueueService delayQueueService;

    public final static String UNPAY = "0";//未推送
    public final static String PAYED = "1";//已推送
    public final static String EXPIRED = "-1";//已过期


    public void insert(int Number) {

        for(int i=0;i<Number;i++) {

                Long time = new Date().getTime();
                Long times = time+60000;
//                Random random = new Random();
//                long expireTime = random.nextInt(20)+5;//超时时长，单位秒5~25
                long expireTime = 60;//超时时长，单位秒60
                //数据库的 保存和过期时间
                Timestamp saveTime=new Timestamp(time);
                Timestamp saveTimes=new Timestamp(times);
                String ST_FJ_ID = time+"_"+expireTime+"_S";//业务编号
                String insertSql = "insert into DANGAN_FJ(ST_FJ_ID,DANGAN_TYPE,TIME,TIMES) values (?,?,?,?)";
                Object[] insertObject = new Object[] {ST_FJ_ID,UNPAY,saveTime,saveTimes};
                SQL.execute(insertSql,insertObject);
                System.out.println("SQL添加标志....");

                /*进行延时处理*/
                delayQueueService.Delay(ST_FJ_ID,expireTime);
        }

    }

    //服务重启的处理
//    @PostConstruct
    public void initDelay(){

        System.out.println("开始扫描未推送的办件....");
        String type = "0";
        String insertSql = "select * from DANGAN_FJ where DANGAN_TYPE = ? ";
        Object[] insertObject = new Object[] {type};
        RecordSet rs = SQL.execute(insertSql,insertObject);
        if(rs.next()){
            Object num = rs.getObject(1);
            System.out.println("共查询出 "+num+" 条记录```````");
        }

        List <String>list0 = new ArrayList<String>();
        List <String>list1 = new ArrayList<String>();
        while (rs.next()){
            //办件Id
            String ST_FJ_ID = rs.getString("ST_FJ_ID");
            //到期时间
            Timestamp latestTime = rs.getTimestamp("TIMES");
            list0.add(ST_FJ_ID);
            Long time = new Date().getTime();
            Long times = latestTime.getTime();
            //未过期的从新放入队列，并计算超时时间
            if(times-time>0){
                list1.add(ST_FJ_ID);
                Long expireTime = (times-time)/1000;
                System.out.println("未过期未推送办件:"+ST_FJ_ID);
                System.out.println("剩余过期时间："+expireTime);
                //放入延迟队列
                delayQueueService.Delay(ST_FJ_ID,expireTime);
            }else{
                //已过期的直接改
                String updateSql = "update dangan_fj set dangan_type =-1 where st_fj_id = ? ";
                Object[] updateObject = new Object[]{ST_FJ_ID};
                RecordSet updateRs = SQL.execute(updateSql, updateObject);
                int number = updateRs.TOTAL_RECORD_COUNT;
                //影响行数
                System.out.println("已过期的直接改-->办件编号为："+ST_FJ_ID+"   办件过期更改影响行数:  " + number);
            }
        }
        System.out.println(String.format("总共有 "+list0.size()+" 条未推送办件....."));
        System.out.println(String.format("总共有 "+list1.size()+" 条未过期未推送办件....."));

    }






    public static void main(String[] args) {
        //当前时间
        Long time = new Date().getTime();
        Long times = time+60000;//+60秒

        //入库时间
        Timestamp saveTime=new Timestamp(time);//SQL时间
        Long sTime = saveTime.getTime();
        //到期时间
        Timestamp saveTime2=new Timestamp(times);//SQL时间
        Long sTime2 = saveTime2.getTime();

        System.out.println(times-time);
        System.out.println(saveTime+"   "+saveTime2);


    }



}
