package com.wondersgroup.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitUtils {
    private static ConnectionFactory connectionFactory = new ConnectionFactory();
    static {
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);//5672是RabbitMQ的默认端口号
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
//        connectionFactory.setVirtualHost("/admin");
    }
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = connectionFactory.newConnection();
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
