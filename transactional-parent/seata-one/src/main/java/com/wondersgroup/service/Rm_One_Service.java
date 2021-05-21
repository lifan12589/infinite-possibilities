package com.wondersgroup.service;


import com.wondersgroup.entity.TbloneInfo;
import com.wondersgroup.mapper.TbloneInfoMapper;
import com.wondersgroup.sqlToJava.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * AT 模式
 */
@Service
public class Rm_One_Service {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    TbloneInfoMapper tbloneInfoMapper;


    public String rm1(){

        long id = SnowFlake.nextId();
        TbloneInfo tbloneInfo = new TbloneInfo();
        tbloneInfo.setId(id+"");
        tbloneInfo.setName("Rm_One_"+id);
        int insert = tbloneInfoMapper.insert(tbloneInfo);

        rm2();

        rm3();

        if (insert==1){
            return id+"";
        }
        return "err";
    }


    public String rm2(){

        restTemplate.getForObject("http://seata-two/two_at", String.class);
        return "";
    }


    public String rm3(){

        restTemplate.getForObject("http://seata-three/three_at", String.class);

        return "";
    }



}
