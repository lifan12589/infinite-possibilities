package com.online.taxi.order.controller;

import com.online.taxi.order.dao.SaveInfoMapper;
import com.online.taxi.order.entity.SaveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class GetInfoByIdController {

    @Autowired
    private SaveInfoMapper saveInfoMapper;

    @GetMapping("/selSql")
    public void selSql(HttpServletRequest request,HttpServletResponse response) throws IOException {

         SaveInfo info = saveInfoMapper.selectInfoByid("001");
         String id = info.getId();
         String applyno = info.getApplyno();
         String address = info.getAddress();
         System.out.println(id);
         System.out.println(applyno);
         System.out.println(address);
         response.getWriter().print("$$$$$$");

    }

    @GetMapping("/InSql")
    public void InSql(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Timestamp saveTime=new Timestamp(new Date().getTime());
        SaveInfo info = new SaveInfo();
        info.setId("002");
        info.setApplyno("004098020000002");
        info.setUserName("测试2");
        info.setUserNumber("002");
        info.setAddress("静安区");
        info.setSaveTime(saveTime);
        info.setType("Yes");
        info.setCount("count");
        int mu = saveInfoMapper.insert(info);
        response.getWriter().print(mu+"");

    }





}
