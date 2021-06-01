package com.infinitepossibilities.mapper;

import com.infinitepossibilities.entity.Tb_User_Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    Tb_User_Info queryUserById(@PathVariable("id") String id);


}
