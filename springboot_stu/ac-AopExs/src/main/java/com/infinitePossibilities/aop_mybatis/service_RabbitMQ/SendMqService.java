package com.infinitePossibilities.aop_mybatis.service_RabbitMQ;

import com.alibaba.fastjson.JSONObject;
import com.infinitePossibilities.aop_mybatis.entity.AopExsInfo;
import com.infinitePossibilities.aop_mybatis.mapper.AopExsInfoMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SendMqService {

    @Autowired
    AopExsInfoMapper aopExsInfoMapper;

    @Autowired
    private AmqpTemplate template;

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void sendData(){

        System.out.println("开始扫描未推送的办件....");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                List<AopExsInfo> list = aopExsInfoMapper.selectNew("new");

                for (AopExsInfo aopExsInfo : list){

                    //先改事件表数据状态
                    AopExsInfo old = new AopExsInfo();
                    old = aopExsInfo;
                    old.setCupSize("old");
                    int i = aopExsInfoMapper.updateByPrimaryKey(old);
//                    System.out.println("更改事件表状态返回： "+i);

                    //开始往MQ里推送

                    JSONObject json = new JSONObject();
                    json.put("id",aopExsInfo.getId());
                    json.put("cupSize",aopExsInfo.getCupSize());
//                    System.out.println("未推送的办件信息 ： "+json.toString());

                    template.convertAndSend(RabbitConstant.QUEUE_HELLOWORLD,json.toString());

                }
            }
        },0,10, TimeUnit.SECONDS);  //每10秒查询一次
    }
}
