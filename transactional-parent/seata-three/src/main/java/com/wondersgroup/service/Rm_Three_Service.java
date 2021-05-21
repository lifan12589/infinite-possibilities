package com.wondersgroup.service;

import com.wondersgroup.entity.TblthreeInfo;
import com.wondersgroup.mapper.TblthreeInfoMapper;
import com.wondersgroup.sqlToJava.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Rm_Three_Service {

    @Autowired
    TblthreeInfoMapper tblthreeInfoMapper;

    public String rm3(){

        long id = SnowFlake.nextId();
        TblthreeInfo tblthreeInfo = new TblthreeInfo();
        tblthreeInfo.setId(id+"");
        tblthreeInfo.setName("Rm_Three_"+id);

        int insert = tblthreeInfoMapper.insert(tblthreeInfo);

        if (insert==1){
            return id+"";
        }
        return "err";
    }



}
