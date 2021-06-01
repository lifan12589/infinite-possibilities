package com.infinitepossibilities.controller;

import com.infinitepossibilities.entity.Tb_User_Info;
import com.infinitepossibilities.mapper.UserMapper;
import com.infinitepossibilities.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    /*
        http://localhost:8081/user?ids=123,456
     */
//    @GetMapping
//    public List<Tb_User_Info> getUser(@RequestParam("ids")List<String> ids){
//
//        List<Tb_User_Info> users = userService.queryByIds(ids);
//
//        return users;
//    }

    /*
        http://localhost:8081/user?hids=123,456,789
     */
    @GetMapping
    public List<Tb_User_Info> getUsers(@RequestParam("hids")List<String> ids){

        List<Tb_User_Info> users = new ArrayList<>();
        for(String id : ids){

            Tb_User_Info user = userMapper.queryUserById(id);
            users.add(user);

        }

        return users;
    }



}
