package com.wondersgroup.service;

import com.wondersgroup.entity.TblthreeInfo;
import com.wondersgroup.mapper.TblthreeInfoMapper;
import com.wondersgroup.sqlToJava.SnowFlake;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Rm_Three_InterfaceImpl implements Rm_Three_Interface {

    @Autowired
    TblthreeInfoMapper tblthreeInfoMapper;

    private static ConcurrentMap<String,String> maps = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public String rm3(BusinessActionContext businessActionContext) {

        long id = SnowFlake.nextId();
        TblthreeInfo tblthreeInfo = new TblthreeInfo();
        tblthreeInfo.setId(id+"");
        tblthreeInfo.setName("Rm_Three_"+id);
        maps.put("id",id+"");
        int insert = tblthreeInfoMapper.insert(tblthreeInfo);

        System.out.println("rm3   try....");

//        System.out.println(1/0);
        return null;
    }

    @Override
    @Transactional
    public boolean rm3Commit(BusinessActionContext businessActionContext) {

        System.out.println("rm3   rm3Commit....");
        return true;
    }

    @Override
    @Transactional
    public boolean rm3Rollback(BusinessActionContext businessActionContext) {

        String id = maps.get("id");
        int i = tblthreeInfoMapper.deleteByPrimaryKey(id);
        System.out.println("rm3   rm3Rollback...."+i);
        return true;
    }
}
