package com.wondersgroup.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.wondersgroup.entity.LocalTableInfo;
import com.wondersgroup.entity.XueHuaInfo;
import com.wondersgroup.mapper.LocalTableInfoMapper;
import com.wondersgroup.mapper.XueHuaInfoMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class AcceptMqService{

    @Autowired
    XueHuaInfoMapper xueHuaInfoMapper;

    @Autowired
    LocalTableInfoMapper localTableInfoMapper;

   @RabbitListener(queues = "helloworld")
   public void acceptData(String info, Channel channel, Message message){

       try {
           //手动 ACK
//           channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
           System.out.println("监听到的数据： "+info);
//           System.out.println("message : " +message);
           JSONObject JSON = JSONObject.parseObject(info);
           XueHuaInfo xueHuaInfo = new XueHuaInfo();
           xueHuaInfo.setId(JSON.getString("id"));
           xueHuaInfo.setName("start");
           xueHuaInfoMapper.insert(xueHuaInfo);

       } catch (Exception e) {
           e.getMessage();
       }
   }



   @PostConstruct
   @Transactional(rollbackFor = Exception.class)
    public void updataData(){

       System.out.println("开始执行本地业务....");

       ScheduledExecutorService Service = Executors.newScheduledThreadPool(2);

       Service.scheduleAtFixedRate(new Runnable() {
           @Override
           public void run() {

               List<XueHuaInfo> infos = xueHuaInfoMapper.selectNew("start");

               System.out.println("本次读取办数量： "+infos.size());
               for(XueHuaInfo info : infos){

                   LocalTableInfo localTableInfo = new LocalTableInfo();
                   localTableInfo.setId(info.getId());
                   localTableInfo.setType(info.getName());
                   localTableInfoMapper.insert(localTableInfo);
                   int i = 1/0;
                   XueHuaInfo endInfo = info;
                   endInfo.setName("end");
                xueHuaInfoMapper.updateEnd(endInfo);
               }
           }
       },0,10, TimeUnit.SECONDS); ////每10秒查询一次
   }
}
