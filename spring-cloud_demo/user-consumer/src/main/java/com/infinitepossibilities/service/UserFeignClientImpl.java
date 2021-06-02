package com.infinitepossibilities.service;

import com.infinitepossibilities.entity.Tb_User_Info;
import org.springframework.stereotype.Component;

/**
 * Feign中的Fallback配置不像Ribbon中那样简单了。
 *
 * 1）首先，我们要定义一个类，实现刚才编写的UserFeignClient，作为fallback的处理类
 *
 * 2）然后在UserFeignClient中，指定刚才编写的实现类
 */
@Component
public class UserFeignClientImpl implements UserFeignClient {
    @Override
    public Tb_User_Info queryUserById(String id) {

        Tb_User_Info tb_user_info = new Tb_User_Info();

        tb_user_info.setId(id);
        tb_user_info.setUserName("用户信息查询出现异常！");

        return tb_user_info;

    }
}
