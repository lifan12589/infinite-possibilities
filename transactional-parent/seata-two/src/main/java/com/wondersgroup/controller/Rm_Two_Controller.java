package com.wondersgroup.controller;

import com.wondersgroup.service.Rm_Two_Interface;
import com.wondersgroup.service.Rm_Two_Service;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rm_Two_Controller {


    @Autowired
    private Rm_Two_Service rm_two_service;

    @Autowired
    private Rm_Two_Interface rm_two_interface;


    @RequestMapping("/two_at")
    public String rm2(){

        String result = rm_two_service.rm2();
//        System.out.println(1/0);
        if(result.equals("err")){
            return "err";
        }
        return "success_"+result;

    }


    @RequestMapping("/two_tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String oneTcc() throws InterruptedException {
        rm_two_interface.rm2(null);
        return "success";
    }





}
