package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class Producer {

    public static void main(String[] args) throws Exception {

        TransactionMQProducer producer = new TransactionMQProducer("TransactionGroup");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.setTransactionListener(new TransactionListener() {
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {

                System.out.println("==e xecuteLocalTransaction==");
                System.out.println("message-body : "+message.getBody());
                System.out.println("message-TransactionId : "+message.getTransactionId());

                try {

                    //业务

                }catch (Exception e){

                    //回滚消息，broker端会删除半消息
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                //执行事务成功，确认提交
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {

                System.out.println("==c heckLocalTransaction==");
                System.out.println("message-body : "+new String(messageExt.getBody()));
                System.out.println("message-TransactionId : "+messageExt.getTransactionId());

                //暂时为未知状态，等待broker回查
                //return LocalTransactionState.UNKNOW;
                //回滚消息，broker端会删除半消息
                //return LocalTransactionState.ROLLBACK_MESSAGE;

                //执行事务成功，确认提交
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        producer.start();
        TransactionSendResult sendResult = producer.sendMessageInTransaction(new Message
                ("TransactionTopic", "事务消息！".getBytes()), null);

        System.out.println("sendResult : "+sendResult);
        producer.shutdown();
        System.out.println("生产者下线！");

    }
}
