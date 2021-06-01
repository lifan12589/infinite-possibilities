package com.infinitepossibilities.service;

import com.infinitepossibilities.entity.Tb_User_Info;
import com.infinitepossibilities.mapper.UserFeignClient;
import com.infinitepossibilities.mapper.UserMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;


    //通过服务名调用
    public List<Tb_User_Info> queryByIds(List<String> ids) {

        List<Tb_User_Info> list = new ArrayList<>();

        String url = "http://user-service/user/";

        for (String id : ids) {

            list.add(this.restTemplate.getForObject(url + id, Tb_User_Info.class));

        }
        return list;
    }




//手动获取地址
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    public List<Tb_User_Info> queryByIds(List<String> ids) {
//
//        List<Tb_User_Info> list = new ArrayList<>();
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//
//        ServiceInstance instance = instances.get(0);
//
//
//        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/user/";
//
//       for (String id : ids) {
//
//           list.add(this.restTemplate.getForObject(url + id, Tb_User_Info.class));
//
//       }
//        return list;
//    }


//调用写死的地址
//    @Autowired
//    private UserMapper userMapper;

//    public List<Tb_User_Info> queryByIds(List<String> ids){
//
//        List<Tb_User_Info> list = new ArrayList<>();
//
//        for (String id : ids){
//
//            Tb_User_Info user = userMapper.queryById(id);
//
//            list.add(user);
//        }
//
//        return list;
//    }

}
