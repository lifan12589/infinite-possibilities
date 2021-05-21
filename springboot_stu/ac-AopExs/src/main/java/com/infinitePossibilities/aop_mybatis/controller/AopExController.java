package com.infinitePossibilities.aop_mybatis.controller;

import com.infinitePossibilities.aop_mybatis.entity.AopExsInfo;
import com.infinitePossibilities.aop_mybatis.mapper.AopExsInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AopExController {

    @Autowired
    private AopExsInfoMapper aopExsInfoMapper;


    //http://localhost:8080/selectById/1
    @RequestMapping(value="/selectById/{id}")
    public @ResponseBody String selectById(@PathVariable("id") String id) {
        AopExsInfo aopExsInfo = aopExsInfoMapper.selectByPrimaryKey(id);
        System.out.println(aopExsInfo);
        if(aopExsInfo==null) return "查无此数据";
        return aopExsInfo.toString();
    }


    //http://localhost:8080/selectById2?id=1
    @RequestMapping(value="/selectById2")
    public @ResponseBody String selectById2(HttpServletRequest request) {
        String id = request.getParameter("id");
        if(id==null) return "id为空！";
        AopExsInfo aopExsInfo = aopExsInfoMapper.selectByPrimaryKey(id);
        System.out.println(aopExsInfo);
        if(aopExsInfo==null) return "查无此数据";
        return aopExsInfo.toString();
    }




    @RequestMapping(value="/insert")
    public @ResponseBody int insert() {
        AopExsInfo aopExsInfo = new AopExsInfo();

        aopExsInfo.setId("2");
        aopExsInfo.setCupSize("B");
        aopExsInfo.setAge(22);
        aopExsInfo.setMoney(200.00);

        int mu = aopExsInfoMapper.insert(aopExsInfo);
        System.out.println(mu);
        return mu;
    }


    @GetMapping(value="/list")
    public @ResponseBody List<AopExsInfo> list(){

        List<AopExsInfo> list = aopExsInfoMapper.selectAll();
        System.out.println(list);
      return list;
    }
}
