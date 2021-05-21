package com.wondersgroup.service;

import com.wondersgroup.entity.TbltwoInfo;
import com.wondersgroup.mapper.TbltwoInfoMapper;
import com.wondersgroup.sqlToJava.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Rm_Two_Service {

    @Autowired
    TbltwoInfoMapper tbltwoInfoMapper;

    public String rm2(){

        long id = SnowFlake.nextId();
        TbltwoInfo tbltwoInfo = new TbltwoInfo();
        tbltwoInfo.setId(id+"");
        tbltwoInfo.setName("Rm_Two_"+id);

        int insert = tbltwoInfoMapper.insert(tbltwoInfo);


        if (insert==1){
            return id+"";
        }
        return "err";
    }





}
