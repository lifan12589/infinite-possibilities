//package com.wondersgroup.demoRabbit;
//
//import org.springframework.amqp.core.ExchangeTypes;
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
// * 多重绑定
// */
//@Component
//public class Receiver3 {
//    boolean flag=true;
//    /**
//     * @RabbitListener:加了该注解的方法表示该方法是一个消费者
//     */
//    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(value = "Queue1"),
//                    exchange = @Exchange(value = "Exchange" , type = ExchangeTypes.DIRECT)
//            )
//    )
//    public void process(Message message) throws Exception {
//        System.out.println(Thread.currentThread().getId()+" 获取Queue1:" + new String(message.getBody()));
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
//
//
//    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(value = "Queue2"),
//                    exchange = @Exchange(value = "Exchange" , type = ExchangeTypes.DIRECT)
//            )
//    )
//    public void process1(Message message) throws Exception {
//        System.out.println(Thread.currentThread().getId()+" 获取Queue2:" + new String(message.getBody()));
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
//
//
//
//
//
//}
