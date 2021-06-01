package com.infinitepossibilities.mapper;


import com.infinitepossibilities.entity.Tb_User_Info;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserMapper {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;


    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public Tb_User_Info queryUserById(String id){

        long begin = System.currentTimeMillis();

        Tb_User_Info user = userFeignClient.queryUserById(id);


        long end = System.currentTimeMillis();
        // 记录访问用时：
        System.out.println("访问用时：" + (end - begin));
        return user;
    }



    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public Tb_User_Info queryById(String id) {

        long begin = System.currentTimeMillis();

        String url = "http://user-service/user/" + id;
        Tb_User_Info user = this.restTemplate.getForObject(url, Tb_User_Info.class);

        long end = System.currentTimeMillis();
        // 记录访问用时：
        System.out.println("访问用时：" + (end - begin));
        return user;
    }


    public Tb_User_Info queryUserByIdFallback(String id){

        Tb_User_Info tb_user_info = new Tb_User_Info();

        tb_user_info.setId(id);
        tb_user_info.setUserName("用户信息查询出现异常！");

        return tb_user_info;
    }



//调用写死的地址
//    public Tb_User_Info queryById(String id){
//
//        String url = "http://localhost:8080/user/"+id;
//
//        System.out.println("url  -->  "+url);
//        Tb_User_Info user = restTemplate.getForObject(url, Tb_User_Info.class);
//
//        return user;

//    }

}
