package com.infinitePossibilities.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class SentinelController {

    @Resource
    private UserService userService;

    public static final String RESOURCE_NAME = "userList";


    /**
     * 1.采用异常处理的形式 (控制台根据 userList 限流)
     * @return
     */
    @RequestMapping("/exception")
    public List<User> exception(){
        System.out.println(" exception 请求 进来 ....");
        List<User> userList = null;

        Entry entry = null;

        try {
            entry = SphU.entry(RESOURCE_NAME);
            userList = userService.getList();
        } catch (BlockException e) {
            List<User> list = new ArrayList<>();
            list.add(new User("exception", "访问资源被限流！"));
            return list;
        }finally {
            if(entry != null){
                System.out.println("执行 finally .....");
                entry.exit();
            }
        }

            return userList;
    }


    /**
     * 2.采用返回布尔值的方式 (控制台根据 userList 限流)
     * @return
     */
    @RequestMapping("/booLean")
    public List<User> booLean(){

        System.out.println(" booLean 请求 进来 ....");

        List<User> userList = null;
        if(SphO.entry(RESOURCE_NAME)){

            try{
                userList = userService.getList();
            }finally {
                System.out.println("执行 finally .....");
                SphO.exit();
            }
        }else {
            List<User> list = new ArrayList<>();
            list.add(new User("booLean", "访问资源被限流！"));
            return list;
        }

        return userList;
    }


    /**
     * 3.采用注解形式，代码无侵入性 (控制台根据 userList 限流)
     * @return
     */
    @RequestMapping("/annotation")
    public List<User> annotation(){

        List<User> userList = userService.queryAllAnnotation();

    return userList;
    }


    /**
     * 4.熔断 降级 (控制台根据 userList 限流)
     * @return
     */
    @RequestMapping("/fallback")
    public List<User> fallback(){

        List<User> userList = userService.queryAllFallback();

        return userList;
    }


    //============================  以下为根据地址限流 ===============================


    /**
     * 5. (控制台根据 /user/dashboard 地址限流)
     * @return
     */
    @RequestMapping("/dashboard")
    public List<User> dashboard(){

        List<User> userList = userService.queryAllDashboard();

        return userList;
    }


    /**
     * 6. (控制台根据 /user/newTest 地址限流)
     * @return
     */
    @RequestMapping("/newTest")
    public List<User> newTest(){

        List<User> userList = userService.NewTest();

        return userList;
    }

    /**
     * 7. (控制台根据 /user/newJson 地址限流)
     * @return
     */
    @RequestMapping("/newJson")
    public @ResponseBody String newJson(){

        JSONObject userList = userService.NewJson();

        return userList.toString();
    }


}
