package com.wondersgroup.pubsub;


import com.wondersgroup.utils.RabbitConstant;
import com.wondersgroup.utils.RabbitUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class WeatherBureau {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitUtils.getConnection();
        //input为  接收控制台输入的数据
        String input = new Scanner(System.in).next();

        Channel channel = connection.createChannel();
        channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER,"" , null , input.getBytes());

        channel.close();
        connection.close();
    }
}
