package com.wondersgroup.controller;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wondersgroup.entity.ConsumerInfo;
import com.wondersgroup.mapper.ConsumerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LcnController {

    @Autowired
    ConsumerInfoMapper consumerInfoMapper;

    @RequestMapping("/addConsumer")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public @ResponseBody String addConsumer(@RequestBody JSONObject json){

        String id = (String) json.get("id");
        System.out.println("获得 id ： "+id);
        ConsumerInfo consumerInfo = new ConsumerInfo();
        consumerInfo.setId(id);
        consumerInfo.setName("li");
        int i = 1/0;
        consumerInfoMapper.insert(consumerInfo);
        return "addConsumer插入成功";
    }




}
