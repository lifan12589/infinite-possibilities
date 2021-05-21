package com.infinitePossibilities;

import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.Sniffer;

import java.io.IOException;

public class HighLevelClient {

    private static RestClientBuilder restClientBuilder = ClientBuilders.geClientBuilder();

    //实例化 客户端
    private static RestHighLevelClient restHighLevelClient;
    //实例化 嗅探器
    private static Sniffer sniffer;

    //开启 client sniffer
    public static RestHighLevelClient getClient() {

        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
        //刷新并更新节点信息
        sniffer = Sniffer.builder(restHighLevelClient.getLowLevelClient())
                .setSniffAfterFailureDelayMillis(5000)
                .build();

        return restHighLevelClient;
    }


    //关闭  sniffer  client
    public void closeRestHighLevelClient(){

        if(restHighLevelClient != null){

            try {
                sniffer.close();
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
