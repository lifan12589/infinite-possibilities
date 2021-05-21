package com.wondersgroup.demoRabbit;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消费多线程----Receiver1
     * @return
     */
    @RequestMapping("/rabbit.do")
    public String Server(){

        String keys = "keys";
        rabbitTemplate.convertAndSend("Exchange","routingKey",keys);
        return "true";
    }


    /**
     * 广播交换机，忽略routingkey，把所有发送到当前交换机的消息全部路由到所有与当前交换机绑定的队列中去。
     * ----Receiver3
     * @return
     */
//    @RequestMapping("/rabbit.do")
//    public String Server(){
//        String keys = "keys";
//        rabbitTemplate.convertAndSend("Exchange","","routingKey1");
//        return "true";
//}



//    @RequestMapping("/rabbit.do")
//    public void Server(HttpServletResponse response) throws IOException {
//        String keys = "keys";
//        rabbitTemplate.convertAndSend("Exchange","routingKey","routingKey1");
//        rabbitTemplate.convertAndSend("Exchange","routingKey.a","routingKey2");
//        rabbitTemplate.convertAndSend("Exchange","routingKey.b","routingKey3");
//        response.getWriter().print("true");
//    }


//    @RequestMapping("/rabbit.do")
//    public void Server(HttpServletResponse response) throws IOException {
//        String msg = "rabbitmq生成者发送失败和消费失败处理方案";
//        try {
//            // 针对网络原因导致连接断开，利用retryTemplate重连10次
//            RetryTemplate retryTemplate = new RetryTemplate();
//            retryTemplate.setRetryPolicy(new SimpleRetryPolicy(10));
//            rabbitTemplate.setRetryTemplate(retryTemplate);
//
//            // 确认是否发到交换机，若没有则存缓存，用另外的线程重发，直接在里面用rabbitTemplate重发会抛出循环依赖错误
//            rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
//                if (!ack) {
//                    // 存缓存操作
//                    System.out.println(msg + "发送失败:" + cause);
//                }
//            });
//
//            // 确认是否发到队列，若没有则存缓存，然后检查exchange, routingKey配置，之后重发
//            rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//                // 存缓存操作
//                System.out.println(new String(message.getBody()) + "找不到队列，Exchange" + exchange + ",routingKey" + routingKey);
//            });
//
//            rabbitTemplate.convertAndSend("Exchange", "routingKey", msg);
//        } catch (AmqpException e) {
//            // 存缓存操作
//            System.out.println(msg + "发送失败:原因重连10次都没连上。");
//        }
//        response.getWriter().print("true");
//    }







//    @RequestMapping("/rabbit2.do")
//    public void Server2(HttpServletResponse response) throws IOException {
//        System.out.println(Thread.currentThread().getId()+"            "+System.nanoTime());
//        String keys = "keys";
//        int count = 1;
//        String st_fj_id = "keys";
//        String updateSql = "update dangan_fj set count = count+? where st_fj_id = ?";
//        Object[] updateObject = new Object[] {count,st_fj_id};
//        RecordSet updateRs  = SQL.execute(updateSql,updateObject);
//        int number = updateRs.TOTAL_RECORD_COUNT;
//        //影响行数
//        System.out.println(Thread.currentThread().getId()+" 数据库影响行数:"+number);
//        response.getWriter().print("true");
//    }



}
