package com.infinitePossibilities.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

public class Producer5 {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("MQ-SX-2Group");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();


        //20条消息顺序写入
        for(int i = 0;i<20;i++){

            Message message = new Message("MQ2-SX-Topic",("顺序--消息!"+i).getBytes());

            producer.send(
                    //要发的消息
                    message,
                    //queue选择器，向topic中的哪个 queue 里写入
                    new MessageQueueSelector() {
                    //手动选择一个 queue
                    public MessageQueue select(
                        //当前topic里包含的所有 queue
                        List<MessageQueue> list,
                        //要发的消息
                        Message message,
                        //对应到 send() 方法的 arg标识
                        Object o) {
                        //向固定一个 queue 里写消息
                        MessageQueue queue = list.get(0);
                        //返回 选好的 queue
                        return queue;
                }

            },0,2000);
        }

        //20条消息顺序写入
        for(int i = 0;i<20;i++){

            Message message = new Message("MQ2-SX-Topic",("顺序--消息!"+i).getBytes());

            producer.send(
                    //要发的消息
                    message,
                    //queue选择器，向topic中的哪个 queue 里写入
                    new MessageQueueSelector() {
                        //手动选择一个 queue
                        public MessageQueue select(
                                //当前topic里包含的所有 queue
                                List<MessageQueue> list,
                                //要发的消息
                                Message message,
                                //对应到 send() 方法的 arg标识
                                Object o) {
                            //向固定一个 queue 里写消息
                            MessageQueue queue = list.get(1);
                            //返回 选好的 queue
                            return queue;
                        }

                    },0,2000);
        }

        //20条消息顺序写入
        for(int i = 0;i<20;i++){

            Message message = new Message("MQ2-SX-Topic",("顺序--消息!"+i).getBytes());

            producer.send(
                    //要发的消息
                    message,
                    //queue选择器，向topic中的哪个 queue 里写入
                    new MessageQueueSelector() {
                        //手动选择一个 queue
                        public MessageQueue select(
                                //当前topic里包含的所有 queue
                                List<MessageQueue> list,
                                //要发的消息
                                Message message,
                                //对应到 send() 方法的 arg标识
                                Object o) {
                            //向固定一个 queue 里写消息
                            MessageQueue queue = list.get(2);
                            //返回 选好的 queue
                            return queue;
                        }

                    },0,2000);
        }

        //20条消息顺序写入
        for(int i = 0;i<20;i++){

            Message message = new Message("MQ2-SX-Topic",("顺序--消息!"+i).getBytes());

            producer.send(
                    //要发的消息
                    message,
                    //queue选择器，向topic中的哪个 queue 里写入
                    new MessageQueueSelector() {
                        //手动选择一个 queue
                        public MessageQueue select(
                                //当前topic里包含的所有 queue
                                List<MessageQueue> list,
                                //要发的消息
                                Message message,
                                //对应到 send() 方法的 arg标识
                                Object o) {
                            //向固定一个 queue 里写消息
                            MessageQueue queue = list.get(3);
                            //返回 选好的 queue
                            return queue;
                        }

                    },0,2000);
        }


//        producer.shutdown();
        System.out.println("生产者下线！");

    }
}
