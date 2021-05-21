package com.infinitePossibilities.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 官网地址 https://github.com/nobodyiam/apollo-build-scripts
 */
@Controller
public class TestController {

    @Value("${name}")
    private String data;

    @RequestMapping("/")
    public @ResponseBody
    String getApolloData(){

        System.out.println(data);

        return "拉取数据 --> : " + data;
    }


}
