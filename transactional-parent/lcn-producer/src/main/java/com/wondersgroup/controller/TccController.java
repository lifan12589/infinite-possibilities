package com.wondersgroup.controller;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.wondersgroup.entity.ProducerInfo;
import com.wondersgroup.mapper.ProducerInfoMapper;
import com.wondersgroup.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Controller
public class TccController {

    @Autowired
    ProducerInfoMapper producerInfoMapper;

    @Autowired
    RestTemplate restTemplate;

    private static ConcurrentMap<String,String> maps = new ConcurrentHashMap<>();


    @RequestMapping("/addProducer-tcc")
    @Transactional(rollbackFor = Exception.class)
    @TccTransaction
    public @ResponseBody String AddProducer(){

        JSONObject json = new JSONObject();
        ProducerInfo producerInfo = new ProducerInfo();
        String id = SnowFlake.nextId()+"";
        System.out.println("Producer 获得Id : "+id);
        maps.put("id",id);
        json.put("id",id);
        producerInfo.setId(id);
        producerInfo.setName("li");
//        restTemplate.postForEntity("http://localhost:8090/addConsumer-tcc",json,String.class);
        restTemplate.postForEntity("http://lcn-consumer/addConsumer-tcc",json,String.class);
//        int i = 1/0;
        int insert = producerInfoMapper.insert(producerInfo);
        System.out.println("Producer 插入结果 ："+insert);
        return "addProducer插入成功";
    }

    public String confirmAddProducer(){

        System.out.println("confirm -- Producer  :  "+maps.get("id"));

        return "";
    }

    public String cancelAddProducer(){

        String id = maps.get("id");
        System.out.println("Producer -- cancel-id : "+id);
        int i = producerInfoMapper.deleteByPrimaryKey(id);
        System.out.println("cancel -- Producer  : " +i);
        return "";
    }



}
