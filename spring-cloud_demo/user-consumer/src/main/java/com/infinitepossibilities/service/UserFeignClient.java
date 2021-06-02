package com.infinitepossibilities.service;

import com.infinitepossibilities.entity.Tb_User_Info;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 首先这是一个接口，Feign会通过动态代理，帮我们生成实现类。
 *
 * @FeignClient，声明这是一个Feign客户端，类似@Mapper注解。同时通过value属性指定服务名称
 *
 * 接口中的定义方法，完全采用SpringMVC的注解，Feign会根据注解帮我们生成URL，并访问获取结果
 */
@FeignClient(value = "user-service",fallback = UserFeignClientImpl.class)
public interface UserFeignClient {

    @GetMapping("/user/{id}")
    Tb_User_Info queryUserById(@PathVariable("id") String id);


}
