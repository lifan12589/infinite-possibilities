//package com.wondersgroup.demoRabbit;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.Exchange;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.QueueBinding;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import wfc.service.database.RecordSet;
//import wfc.service.database.SQL;
//
///**
// * 消费多线程
// */
//@Component
//public class Receiver4 {
//    boolean flag=true;
//    /**
//     * @RabbitListener:加了该注解的方法表示该方法是一个消费者
//     * concurrency：并发数量。可在配置文件加 spring.rabbitmq.listener.simple.concurrency: 5
//     */
//    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(value = "myQueue"),
//                    exchange = @Exchange(value = "Exchange"),
//                    key = "routingKey.#"
//            )
////   ,concurrency =  "5"
//    )
//    public void process(Message message) throws Exception {
//        System.out.println(Thread.currentThread().getId()+" 获取myQueue:" + new String(message.getBody()));
//
//
//        if(flag){
//            int count = 1;
//            String st_fj_id = "keys";
//            String updateSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=1";
//            Object[] updateObject = new Object[] {count,st_fj_id};
//            RecordSet updateRs  = SQL.execute(updateSql,updateObject);
//            int number = updateRs.TOTAL_RECORD_COUNT;
//            //影响行数
//            System.out.println(Thread.currentThread().getId()+" 数据库影响行数:"+number);
//            if(number==0){
//                flag=false;
//            }
//        }else {
//            System.out.println(Thread.currentThread().getId()+" 数据库参数为0！");
//        }
//        Thread.sleep(2000);
//    }
//
//}
