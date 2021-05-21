package com.infinitePossibilities.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getList() {

        List<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan"));
        list.add(new User("2", "liusi"));
        list.add(new User("3", "wangwu"));

        return list;
    }


    //注解形式   value是资源名称，是必填项。blockHandler填限流处理的方法名称
    @Override
    @SentinelResource(value=SentinelController.RESOURCE_NAME,blockHandler = "annotation")
    public List<User> queryAllAnnotation() {
        System.out.println("正常请求.....");
        List<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan"));
        list.add(new User("2", "liusi"));
        list.add(new User("3", "wangwu"));

        return list;
    }

    //注解限流处理方法
    public List<User> annotation(BlockException ex){

        System.out.println("请求频繁，进入注解限流.....");
        List<User> list = new ArrayList<>();
        list.add(new User("annotation", "访问资源被限流！"));
        return list;
    }


    // 抛出非 BlockException 的异常时，就会进入到fallback方法中，实现熔断机制
    @Override
    @SentinelResource(value=SentinelController.RESOURCE_NAME,fallback = "fallback")
    public List<User> queryAllFallback() {
        System.out.println("正常请求.....");
        List<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan"));
        list.add(new User("2", "liusi"));
        list.add(new User("3", "wangwu"));
        if(list.size()!=0){

            throw new RuntimeException("list is not null ...");
        }

        return list;
    }

    //熔断降级处理方法
    public List<User> fallback(Throwable ex){

        System.out.println("访问资源异常,进入 fallback .....");
        System.out.println("异常信息---> : ");
        ex.printStackTrace();
        List<User> list = new ArrayList<>();
        list.add(new User("fallback", "访问资源异常,降级处理！"));
        return list;
    }


    @Override
    @SentinelResource(value="queryAllDashboard",fallback = "dashboardFallback")
    public List<User> queryAllDashboard() {
        System.out.println("Dashboard正常请求.....");
        List<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan"));
        list.add(new User("2", "liusi"));
        list.add(new User("3", "wangwu"));

        return list;
    }

    public List<User> dashboardFallback(){

        System.out.println("Dashboard访问资源频繁,进入 fallback .....");

        List<User> list = new ArrayList<>();
        list.add(new User("Dashboard-fallback", "访问资源频繁,降级处理！"));
        return list;
    }


    @Override
    @SentinelResource(value= "NewTest" ,fallback ="newFallback" )
    public List<User> NewTest() {
        System.out.println("NewTest正常请求.....");
        List<User> list = new ArrayList<>();

        list.add(new User("1", "zhangsan"));
        list.add(new User("2", "liusi"));
        list.add(new User("3", "wangwu"));

        return list;
    }



    public List<User> newFallback(){

        System.out.println("NewTest访问资源频繁,进入 fallback .....");

        List<User> list = new ArrayList<>();
        list.add(new User("NewTest-fallback", "访问资源频繁,降级处理！"));
        return list;
    }


    public List<User> exceptionHandler(BlockException ex) {

        System.out.println("NewTest 异常......");
        ex.printStackTrace();
        List<User> list = new ArrayList<>();
        list.add(new User("NewTest-Exception", "访问资源异常,降级处理！"));
        return list;
    }



    @Override
    @SentinelResource(value = "NewJson",fallback = "JsonFallback")
    public JSONObject NewJson() {

        System.out.println("NewJson 正常请求.....");
       JSONObject json = new JSONObject();

        json.put("1","zhangsan");
        json.put("2","liusi");
        json.put("3","wangwu");

        return json;
    }


    public JSONObject JsonFallback(){

        System.out.println("NewJson 访问资源频繁,进入 fallback .....");

        JSONObject json = new JSONObject();
        json.put("fallback","访问资源频繁,降级处理！");
        return json;
    }

}
