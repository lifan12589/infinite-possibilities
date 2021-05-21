package com.wondersgroup.controller;


import com.wondersgroup.entity.AopExsInfo;
import com.wondersgroup.entity.XueHuaInfo;
import com.wondersgroup.mapper.AopExsInfoMapper;
import com.wondersgroup.mapper.XueHuaInfoMapper;
import com.wondersgroup.uitl.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TransactionalController {

    @Autowired
    private AopExsInfoMapper aopExsInfoMapper;

    @Autowired
    private XueHuaInfoMapper xueHuaInfoMapper;

    @RequestMapping("/insertTran")
    @Transactional(rollbackFor = Exception.class)
    public @ResponseBody
    String insert(){

        AopExsInfo aopExsInfo = new AopExsInfo();
        XueHuaInfo xueHuaInfo = new XueHuaInfo();
        aopExsInfo.setId("2");
        aopExsInfo.setCupSize("B");
        aopExsInfo.setMoney(50.00);
        aopExsInfo.setAge(20);
        int Anum = aopExsInfoMapper.insert(aopExsInfo);
        xueHuaInfo.setId("2");
        xueHuaInfo.setName("B");
        int Xnum = xueHuaInfoMapper.insert(xueHuaInfo);
     return "执行结果 ： "+Anum+"---"+Xnum;
    }


    @RequestMapping("/updateTran")
    @Transactional
    public @ResponseBody
    String update(){

        AopExsInfo aopExsInfo = new AopExsInfo();
        XueHuaInfo xueHuaInfo = new XueHuaInfo();

        xueHuaInfo.setId("5");
        xueHuaInfo.setName("D");
        int Xnum = xueHuaInfoMapper.insert(xueHuaInfo);

        Double money = 100.00;
        aopExsInfo.setId("2");
        aopExsInfo.setCupSize("B");
        aopExsInfo.setMoney(money);
        aopExsInfo.setAge(20);

        int Anum = aopExsInfoMapper.updateByPrimaryKey2(aopExsInfo);

        return "执行结果 ： "+Xnum+"---"+Anum;
    }


    /**
     * 分布式事务入口
     * @return
     * @throws Exception
     */
    @RequestMapping("/producerMq")
    public @ResponseBody
    String mq() throws Exception {

        for(int i = 0;i<10;i++){

            //雪花算法生成id
            Long userId = SnowFlake.nextId();
            AopExsInfo aopExsInfo = new AopExsInfo();
            aopExsInfo.setId(userId+"_ProducerMq_"+i);
            aopExsInfo.setCupSize("new");
            aopExsInfo.setMoney(100.00);
            aopExsInfo.setAge(20);
            int Anum = aopExsInfoMapper.insert(aopExsInfo);
            System.out.println("新增结果： "+Anum);
        }
        return "新增完成";
    }





}
