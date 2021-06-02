package com.infinitepossibilities.service;

import com.infinitepossibilities.entity.Tb_User_Info;
import com.infinitepossibilities.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    //调用写死的地址
    public List<Tb_User_Info> queryByIdsFixed(List<String> ids){

        List<Tb_User_Info> list = new ArrayList<>();

        for (String id : ids){

            Tb_User_Info user = userMapper.queryByIdsFixed(id);

            list.add(user);
        }

        return list;
    }

    //手动获取地址DC
    public List<Tb_User_Info> queryByIdsHandDC(List<String> ids) {

        List<Tb_User_Info> list = new ArrayList<>();

        //用服务名找列表
        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");

        if(instances.size()>0){

            //获取第一个
            ServiceInstance instance = instances.get(0);

            //组装url
            String url = "http://"+instance.getHost()+":"+instance.getPort()+"/user/";

            System.out.println("url --->:" +url);

            for (String id : ids) {

                list.add(this.restTemplate.getForObject(url + id, Tb_User_Info.class));

            }
            return list;

        }else{
            //未找到可用服务
            Tb_User_Info tb_user_info = new Tb_User_Info();
            tb_user_info.setId("500");
            tb_user_info.setUserName("用户信息查询出现异常！");
            list.add(tb_user_info);
        }
        return list;
    }


    //手动获取地址LBC
    public List<Tb_User_Info> queryByIdsHandLBC(List<String> ids) {

        List<Tb_User_Info> list = new ArrayList<>();

        ServiceInstance instance = loadBalancerClient.choose("user-service");

        if(instance != null){

            String url = "http://"+instance.getHost() + ":" + instance.getPort() + "/user/";
            System.out.println("url --->:" +url);

            for (String id : ids) {

                list.add(this.restTemplate.getForObject(url + id, Tb_User_Info.class));

            }
            return list;

        }else{

            //未找到可用服务
            Tb_User_Info tb_user_info = new Tb_User_Info();
            tb_user_info.setId("500");
            tb_user_info.setUserName("用户信息查询出现异常！");
            list.add(tb_user_info);
        }

        return list;
    }


    //通过服务名调用
    public List<Tb_User_Info> queryByIds(List<String> ids) {

        List<Tb_User_Info> list = new ArrayList<>();

        String url = "http://user-service/user/";

        for (String id : ids) {

            list.add(this.restTemplate.getForObject(url + id, Tb_User_Info.class));

        }
        return list;
    }


}
