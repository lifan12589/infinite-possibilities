package com.infinitePossibilities.util;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.Sniffer;

import java.io.IOException;

public class ESClient {

    private static ESClient esClient;
    private String host = "localhost:9200,localhost:9201,localhost:9202";
    private RestClientBuilder builder;
    private static Sniffer sniffer;
    private  static RestHighLevelClient highLevelClient;

    private ESClient(){

    }

    public static ESClient getEsClient(){

        if(esClient == null){

            synchronized (ESClient.class){
                if(esClient == null){
                   esClient = new ESClient();
                   esClient.initBuilder();
                }
            }
        }
       return esClient;
    }


    public RestClientBuilder initBuilder(){

        String [] hosts = host.split(",");

        HttpHost[] httpHosts = new HttpHost[hosts.length];
        for(int i = 0;i<hosts.length;i++){
            String [] host = hosts[i].split(":");
            httpHosts[i] = new HttpHost(host[0],Integer.parseInt(host[1]),"http");
        }

        //region 在Builder中设置请求头
        //  1.设置请求头
        builder = RestClient.builder(httpHosts);
        Header[] defaultHeader = new Header[]{
                new BasicHeader("Content-type","application/json")
        };
        builder.setDefaultHeaders(defaultHeader);

        return builder;
    }

    public RestHighLevelClient getHighLevelClient(){

        if(highLevelClient == null){
            synchronized (ESClient.class){
                if(highLevelClient == null){
                    highLevelClient = new RestHighLevelClient(builder);
                    //开启嗅探器
                    sniffer = Sniffer.builder(highLevelClient.getLowLevelClient())
                            .setSniffIntervalMillis(5000)  //设置连续两次普通嗅探执行之间的间隔（以毫秒为单位）
                            .setSniffAfterFailureDelayMillis(15000)  //设置失败后计划执行嗅探的延迟（以毫秒为单位）
                            .build();
                }
            }
        }
        return highLevelClient;
    }

    public void closeClient(){

        if(null!=highLevelClient){

            try {
                sniffer.close();
                highLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
