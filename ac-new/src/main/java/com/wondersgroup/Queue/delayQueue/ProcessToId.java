package com.wondersgroup.Queue.delayQueue;


import org.springframework.stereotype.Service;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

@Service
public class ProcessToId {


    public void doProcess(String ST_FJ_ID){

        String insertSql = "select * from DANGAN_FJ where ST_FJ_ID = ? ";
        Object[] insertObject = new Object[] {ST_FJ_ID};
        RecordSet rs = SQL.execute(insertSql,insertObject);

        String type="";
        while (rs.next()){
            type = rs.getString("DANGAN_TYPE");
        }
        if(type.equals(DelayQueueSave.UNPAY)){

            System.out.println("办件【"+ST_FJ_ID+"】已过期，需要更改为过期办件！");

            String updateSql = "update dangan_fj set dangan_type =-1 where st_fj_id = ? ";
            Object[] updateObject = new Object[]{ST_FJ_ID};
            RecordSet updateRs = SQL.execute(updateSql, updateObject);
            int number = updateRs.TOTAL_RECORD_COUNT;
            //影响行数
            System.out.println("办件过期更改影响行数:  " + number+"   办件编号为："+ST_FJ_ID);
        }

    }





}
