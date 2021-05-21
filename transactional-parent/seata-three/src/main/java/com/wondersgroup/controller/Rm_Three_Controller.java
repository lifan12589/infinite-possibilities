package com.wondersgroup.controller;

import com.wondersgroup.service.Rm_Three_Interface;
import com.wondersgroup.service.Rm_Three_Service;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rm_Three_Controller {


    @Autowired
    private Rm_Three_Service rm_three_service;

    @Autowired
    private Rm_Three_Interface rm_three_interface;


    @RequestMapping("/three_at")
    public String rm3(){

        String result = rm_three_service.rm3();
        System.out.println(1/0);
        if(result.equals("err")){
            return "err";
        }
        return "success_"+result;

    }


    @RequestMapping("/three_tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String oneTcc() throws InterruptedException {
        rm_three_interface.rm3(null);
        return "success";
    }



}
