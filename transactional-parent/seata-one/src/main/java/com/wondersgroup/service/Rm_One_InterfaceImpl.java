package com.wondersgroup.service;

import com.wondersgroup.entity.TbloneInfo;
import com.wondersgroup.mapper.TbloneInfoMapper;
import com.wondersgroup.sqlToJava.SnowFlake;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class Rm_One_InterfaceImpl implements Rm_One_Interface {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TbloneInfoMapper tbloneInfoMapper;

    private static ConcurrentMap<String,String> maps = new ConcurrentHashMap<>();

    @Override
    @Transactional
    public String rm1(BusinessActionContext businessActionContext) {

        long id = SnowFlake.nextId();
        TbloneInfo tbloneInfo = new TbloneInfo();
        tbloneInfo.setId(id+"");
        tbloneInfo.setName("Rm_One_"+id);
        maps.put("id",id+"");
        int insert = tbloneInfoMapper.insert(tbloneInfo);
        System.out.println("rm1  try....."+insert);
        rm2();
        rm3();

        return null;
    }

    @Override
    @Transactional
    public boolean rm1Commit(BusinessActionContext businessActionContext) {
        System.out.println("rm1  rm1Commit...");
        return true;
    }

    @Override
    @Transactional
    public boolean rm1Rollback(BusinessActionContext businessActionContext) {

        String id = maps.get("id");
        int i = tbloneInfoMapper.deleteByPrimaryKey(id);
        System.out.println("rm1  rm1Rollback..."+i);
        return true;
    }



    public String rm2(){

        restTemplate.getForObject("http://seata-two/two_tcc", String.class);
        return "";
    }


    public String rm3(){

        restTemplate.getForObject("http://seata-three/three_tcc", String.class);

        return "";
    }




}
