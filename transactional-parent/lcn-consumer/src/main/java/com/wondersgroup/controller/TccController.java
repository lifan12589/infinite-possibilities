package com.wondersgroup.controller;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.wondersgroup.entity.ConsumerInfo;
import com.wondersgroup.mapper.ConsumerInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class TccController {

    @Autowired
    ConsumerInfoMapper consumerInfoMapper;

    private static ConcurrentHashMap<String,String> maps = new ConcurrentHashMap<>();

    @RequestMapping("/addConsumer-tcc")
    @Transactional(rollbackFor = Exception.class)
    @TccTransaction
    public @ResponseBody String AddConsumer(@RequestBody JSONObject json){

        System.out.println("请求进来.......");
        ConsumerInfo consumerInfo = new ConsumerInfo();
        String id = (String) json.get("id");
        System.out.println("Consumer 获得Id : "+id);
        maps.put("id",id);
        consumerInfo.setId(id);
        consumerInfo.setName("li");
//        int i = 1/0;
        int insert = consumerInfoMapper.insert(consumerInfo);
        System.out.println("Consumer 插入结果 ："+insert);
        return "addConsumer插入成功";
    }

    public String confirmAddConsumer(@RequestBody JSONObject json){

        System.out.println("confirm -- Consumer  :"+maps.get("id"));

        return "";
    }

    public String cancelAddConsumer(@RequestBody JSONObject json){

        String id = maps.get("id");
        System.out.println("Consumer -- cancel-id : "+id);
        int i = consumerInfoMapper.deleteByPrimaryKey(id);
        System.out.println("cancel -- Consumer  :  " +i);
        return "";
    }


}
