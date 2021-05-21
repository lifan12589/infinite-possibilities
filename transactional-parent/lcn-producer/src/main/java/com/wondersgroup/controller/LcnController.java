package com.wondersgroup.controller;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wondersgroup.entity.ProducerInfo;
import com.wondersgroup.mapper.ProducerInfoMapper;
import com.wondersgroup.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class LcnController {

    @Autowired
    ProducerInfoMapper producerInfoMapper;

    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/addProducer")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public @ResponseBody String addProducer(){

        JSONObject json = new JSONObject();
        String id = SnowFlake.nextId()+"";
        json.put("id",id);
        System.out.println("获得Id : "+id);
        ProducerInfo producerInfo = new ProducerInfo();
        producerInfo.setId(id);
        producerInfo.setName("li");
//        restTemplate.postForEntity("http://localhost:8090/addConsumer",json,String.class);
        restTemplate.postForEntity("http://lcn-consumer/addConsumer",json,String.class);
//        int i = 1/0;
        producerInfoMapper.insert(producerInfo);
        return "addProducer插入成功";
    }

}
