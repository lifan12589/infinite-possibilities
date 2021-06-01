package com.infinitePossibilities.controller;

import com.infinitePossibilities.entity.Tb_User_Info;
import com.infinitePossibilities.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参考地址：
 * https://blog.csdn.net/qq_43652509/category_8462341.html
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    /*
        http://localhost:8080/user/123
     */
    @RequestMapping("/{id}")
    public Tb_User_Info queryById(@PathVariable("id")String id) {

        System.out.println("请求进来......"+id);
        Tb_User_Info tb_user_info = userService.queryById(id);
        return tb_user_info;
    }
}
