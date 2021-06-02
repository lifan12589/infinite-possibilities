package com.infinitepossibilities.controller;

import com.infinitepossibilities.entity.Tb_User_Info;
import com.infinitepossibilities.mapper.UserMapper;
import com.infinitepossibilities.service.UserService;
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


    /**
     * 固定写死的地址
     * 需要去掉  RestTemplate   的 @LoadBalanced  注解
     * @param ids
     * @return
     */
    @GetMapping("/fixed")
    public List<Tb_User_Info> getUserFixed(@RequestParam("ids")List<String> ids){

        List<Tb_User_Info> users = userService.queryByIdsFixed(ids);

        return users;
    }

    /**
     * 手动获取地址
     * 需要去掉  RestTemplate   的 @LoadBalanced  注解
     * @param ids
     * @return
     */
    @GetMapping("/handDC")
    public List<Tb_User_Info> getUserHand(@RequestParam("ids")List<String> ids){

        List<Tb_User_Info> users = userService.queryByIdsHandDC(ids);

        return users;
    }


    /**
     * 手动获取地址
     * 需要去掉  RestTemplate   的 @LoadBalanced  注解
     * @param ids
     * @return
     */
    @GetMapping("/handLBC")
    public List<Tb_User_Info> getUserHandLBC(@RequestParam("ids")List<String> ids){

        List<Tb_User_Info> users = userService.queryByIdsHandLBC(ids);

        return users;
    }



    /*
        http://localhost:8081/user?ids=123,456
     */
    @GetMapping
    public List<Tb_User_Info> getUser(@RequestParam("ids")List<String> ids){

        System.out.println("请求进来.....");
        List<Tb_User_Info> users = userService.queryByIds(ids);

        return users;
    }

    /*
        http://localhost:8081/user/hystrix?hids=123,456,789
     */
    @GetMapping("/hystrix")
    public List<Tb_User_Info> getUsersHystrix(@RequestParam("hids")List<String> ids){

        List<Tb_User_Info> users = new ArrayList<>();
        for(String id : ids){

            Tb_User_Info user = userMapper.queryByIdHystrix(id);
            users.add(user);

        }

        return users;
    }

    /**
     * http://localhost:8081/user/feign?hids=123,456,789
     * @param ids
     * @return
     */
    @GetMapping("/feign")
    public List<Tb_User_Info> getUsersFeign(@RequestParam("hids")List<String> ids){

        List<Tb_User_Info> users = new ArrayList<>();
        for(String id : ids){

            Tb_User_Info user = userMapper.queryUserByIdFeign(id);
            users.add(user);
        }

        return users;
    }


//    @GetMapping("/feignClient")
//    public List<Tb_User_Info> getUsersFeignClient(@RequestParam("hids")List<String> ids){
//
//        List<Tb_User_Info> users = new ArrayList<>();
//        for(String id : ids){
//
//            Tb_User_Info user = userMapper.queryUserByIdFeignClient(id);
//            users.add(user);
//        }
//
//        return users;
//    }


}
