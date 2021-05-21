package com.infinitePossibilities;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

public class ClientBuilders {

    private static final String CLUSTER_HOSTNAME_PORT = "Localhost:9200,Localhost:9201,Localhost:9202";


    public static void main(String[] args) {

    }



    public static RestClientBuilder geClientBuilder(){

        String [] hostNamePort = CLUSTER_HOSTNAME_PORT.split(",");
        String host;
        int port;
        String [] temp;
        RestClientBuilder restClientBuilder = null;

        // RestClient 初始化
        if (0!=hostNamePort.length){
            for(String hostPort : hostNamePort){

                temp = hostPort.split(":");
                host = temp [0].trim();
                port = Integer.parseInt(temp [1].trim());
                restClientBuilder = RestClient.builder(new HttpHost(host,port,"http"));
            }
        }

        //restClientBuilder 在构建 restClient 时,可设置以下可配置参数：

        //设置请求头,避免每个请求都必须指定
        Header [] defanltHeader = new Header[]{
                new BasicHeader("header","application/json")
        };
        restClientBuilder.setDefaultHeaders(defanltHeader);

        //设置每次节点发生故障时 , 收到通知的监听器 ; 内部嗅探到故障时被启用
        restClientBuilder.setFailureListener(new RestClient.FailureListener(){
           public void onFailure(Node node){
              super.onFailure(node);
           }
        });

        //设置连接超时时间
//        restClientBuilder.setRequestConfigCallback(restCofigBuilder -> restCofigBuilder.setSocketTimeout(10000));
        restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setSocketTimeout(10000);
                return builder;
            }
        });

        return restClientBuilder;
    }
}
