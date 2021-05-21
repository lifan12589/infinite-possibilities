package com.infinitePossibilities.aop_mybatis.controller;


import com.infinitePossibilities.aop_mybatis.entity.XueHuaInfo;
import com.infinitePossibilities.aop_mybatis.mapper.XueHuaInfoMapper;
import com.infinitePossibilities.aop_mybatis.uitl.RedisUtil;
import com.infinitePossibilities.aop_mybatis.uitl.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XueHuaController {

    @Autowired
    private XueHuaInfoMapper xueHuaInfoMapper;

    @RequestMapping(value="/xuehua")
    public String insert2() {
        for (int i = 0; i < 1000000; i++) {
            Long userId = SnowFlake.nextId();
//            System.out.println(userId);
            String id = userId.toString();
            XueHuaInfo xueHuaInfo = new XueHuaInfo();
            xueHuaInfo.setId(id);
            xueHuaInfo.setName("xuehua");
            int number = 0;
            try {
                number = xueHuaInfoMapper.insert(xueHuaInfo);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(id);
                boolean serchRedis = RedisUtil.set(userId.toString(),userId.toString());
                System.out.println(serchRedis);
            }

        }
        return "";
    }


}
