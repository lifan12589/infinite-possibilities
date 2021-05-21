//package com.infinitePossibilities.aop_mybatis.service_RabbitMQ;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.infinitePossibilities.aop_mybatis.entity.AopExsInfo;
//import com.infinitePossibilities.aop_mybatis.mapper.AopExsInfoMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//@Service
//public class SendMqService2 {
//
//    @Autowired
//    AopExsInfoMapper aopExsInfoMapper;
//
//    Connection conn;
//    //创建通信“通道”，相当于TCP中的虚拟连接
//    Channel channel;
//
//
//    @PostConstruct
//    @Transactional
//    public void sendData() throws Exception{
//        System.out.println("开始扫描未推送的办件....");
//
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
//
//        conn= RabbitUtils.getConnection();
//
//        channel = conn.createChannel();
//
//        channel.queueDeclare(RabbitConstant.QUEUE_HELLOWORLD, false, false, false, null);
//
//        executor.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//
//                List<AopExsInfo> list = aopExsInfoMapper.selectNew("new");
//
//                for (AopExsInfo aopExsInfo : list){
//
//                    //先改事件表数据状态
//                    AopExsInfo old = new AopExsInfo();
//                    old = aopExsInfo;
//                    old.setCupSize("old");
//                    int i = aopExsInfoMapper.updateByPrimaryKey(old);
//                    System.out.println("更改事件表状态返回： "+i);
//
//                    //开始往MQ里推送
//                    System.out.println("未推送的办件信息 ： "+aopExsInfo);
//
//                    String message = aopExsInfo.toString();
//
//                    try {
//                        channel.basicPublish("" , RabbitConstant.QUEUE_HELLOWORLD,null  , message.getBytes());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if(list.size()==0){
//                        try {
//                            channel.close();
//                            conn.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (TimeoutException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println("发送数据成功");
//                }
//            }
//        },0,10, TimeUnit.SECONDS);
//    }
//}
