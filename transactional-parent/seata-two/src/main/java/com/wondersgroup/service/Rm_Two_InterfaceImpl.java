package com.wondersgroup.service;

import com.wondersgroup.entity.TbltwoInfo;
import com.wondersgroup.mapper.TbltwoInfoMapper;
import com.wondersgroup.sqlToJava.SnowFlake;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Rm_Two_InterfaceImpl implements Rm_Two_Interface{



    @Autowired
    TbltwoInfoMapper tbltwoInfoMapper;

    private static ConcurrentMap<String,String> maps = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public String rm2(BusinessActionContext businessActionContext) {

        long id = SnowFlake.nextId();
        TbltwoInfo tbltwoInfo = new TbltwoInfo();
        tbltwoInfo.setId(id+"");
        tbltwoInfo.setName("Rm_Two_"+id);
        maps.put("id",id+"");
        int insert = tbltwoInfoMapper.insert(tbltwoInfo);

        System.out.println("rm2   try...."+insert);

        return null;
    }

    @Override
    @Transactional
    public boolean rm2Commit(BusinessActionContext businessActionContext) {

        System.out.println("rm2   rm2Commit....");
        return true;
    }

    @Override
    @Transactional
    public boolean rm2Rollback(BusinessActionContext businessActionContext) {

        String id = maps.get("id");
        int i = tbltwoInfoMapper.deleteByPrimaryKey(id);
        System.out.println("rm2   rm2Rollback...."+i);
        return true;
    }
}
