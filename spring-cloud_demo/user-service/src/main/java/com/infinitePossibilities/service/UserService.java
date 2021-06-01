package com.infinitePossibilities.service;

import com.infinitePossibilities.entity.Tb_User_Info;
import com.infinitePossibilities.mapper.Tb_User_InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    @Autowired
    private Tb_User_InfoMapper tb_user_infoMapper;


    public Tb_User_Info queryById(String id){

        //测试 hystrix 的熔断降级 默认1000毫秒
//        try {
//            Thread.sleep(new Random().nextInt(2000));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Tb_User_Info tb_user_info = tb_user_infoMapper.selectByPrimaryKey(id);

        return tb_user_info;

    }



}
