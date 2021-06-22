package com.infinitepossibilities.Text;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PzController {

    @NacosValue(value = "${ac-username}" ,autoRefreshed = true)
    private String username;

    @NacosValue(value = "${ac-password}" ,autoRefreshed = true)
    private String password;



    @RequestMapping("/get")
    public String getData(){

        System.out.println("username : "+username);
        System.out.println("password : "+password);
      return username+"   -   "+password;

    }

}
