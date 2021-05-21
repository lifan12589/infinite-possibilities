package com.wondersgroup.controller;

import com.wondersgroup.service.Rm_One_Interface;
import com.wondersgroup.service.Rm_One_Service;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rm_One_Controller {

    @Autowired
    private Rm_One_Service rm_one_service;

    @Autowired
    private Rm_One_Interface rm_one_interface;


    /**
     * AT 模式
     * @return
     */
    @RequestMapping("/one_at")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String at_one(){

        String result = rm_one_service.rm1();
        if(result.equals("err")){
            return "err";
        }
        return "success_"+result;
    }


    /**
     * TCC模式
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/one_tcc")
    @GlobalTransactional(rollbackFor = Exception.class)
    public String oneTcc() throws InterruptedException {
        rm_one_interface.rm1(null);
        return "success";
    }


}
