package com.infinitepossibilities.mapper;


import com.infinitepossibilities.entity.Tb_User_Info;
//import com.infinitepossibilities.service.queryUserByIdFeignClient;
import com.infinitepossibilities.service.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserMapper {

    @Autowired
    private RestTemplate restTemplate;



    //调用写死的地址
    public Tb_User_Info queryByIdsFixed(String id){

        String url = "http://localhost:8080/user/"+id;

        System.out.println("url  -->  "+url);
        Tb_User_Info user = restTemplate.getForObject(url, Tb_User_Info.class);

        return user;

    }


    /**
     * Hystrix 降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public Tb_User_Info queryByIdHystrix(String id) {

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


    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * Feign
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public Tb_User_Info queryUserByIdFeign(String id){

        long begin = System.currentTimeMillis();

        Tb_User_Info user = userFeignClient.queryUserById(id);

        long end = System.currentTimeMillis();
        // 记录访问用时：
        System.out.println("访问用时：" + (end - begin));
        return user;
    }

//    @Autowired
//    private queryUserByIdFeignClient queryUserByIdFeignClient;
//    /**
//     * Feign
//     * @param id
//     * @return
//     */
//    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
//    public Tb_User_Info queryUserByIdFeignClient(String id){
//
//        long begin = System.currentTimeMillis();
//
//        Tb_User_Info user = queryUserByIdFeignClient.queryUserByIdFeignCliend(id);
//
//        long end = System.currentTimeMillis();
//        // 记录访问用时：
//        System.out.println("访问用时：" + (end - begin));
//        return user;
//    }

}
