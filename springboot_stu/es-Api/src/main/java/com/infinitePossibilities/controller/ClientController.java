package com.infinitePossibilities.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.infinitePossibilities.dto.Bulk_Info;
import com.infinitePossibilities.util.ESClient;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.InetAddress;

@RestController
public class ClientController {

    RestHighLevelClient client = ESClient.getEsClient().getHighLevelClient();

    @GetMapping("/transport")
    @SneakyThrows
    public String transport() {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9301))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9302));

        Settings settings = Settings.builder()
                .put("cluster.name", "myClusterName").build();
        client = new PreBuiltTransportClient(settings);
        //Add transport addresses and do something with the client...
        client.close();
        return "";
    }

    public String indexInit() throws IOException {

        CreateIndexRequest request  = new CreateIndexRequest("index_init_contro");

        //方式一：
        request.mapping("_doc",
                "{\n" +
                                " \"properties\": {\n" +
                                "   \"message\": {\n" +
                                "     \"type\": \"text\"\n" +
                                "    }\n" +
                                "  }\n" +
                                "}", XContentType.JSON);
        //设置 分片 和 副本
        request.settings(Settings.builder()
                .put("index.number_of_shards",3)
                .put("index.number_of_replicas",2));

        //方式二：
//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder.startObject();
//        {
//            builder.startObject("properties");
//            {
//                builder.startObject("message");
//                {
//                   builder.field("type","text");
//                }
//                builder.endObject();
//            }
//            builder.endObject();
//        }
//        builder.endObject();
//       request.mapping(String.valueOf(builder));
//       request.settings(Settings.builder()
//               .put("index.number_of_shards",3)
//               .put("index.number_of_replicas",2));



        //创建索引 返回值
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        if(createIndexResponse.isAcknowledged()){
            System.out.println("创建index成功！");
        }else {
            System.out.println("创建index失败！");
        }

        return createIndexResponse.index();

    }

    // 分页 查询
    public JSONArray getInfo() throws IOException {

        Bulk_Info bulk_Info = new Bulk_Info();

        SearchRequest searchRequest = new SearchRequest("bulk_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("targettype","个人"));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(3);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);

        bulk_Info.setData(searchResponse.getHits().getHits());
        System.out.println(searchResponse);
        SearchHit[] hits = searchResponse.getHits().getHits();

        JSONArray array = new JSONArray();
        for (int i =0;i<hits.length;i++){
            JSONObject json = JSONObject.parseObject(hits[i].toString());
            array.add(json);
        }

        client.close();
        return array;
    }









    public static void main(String[] args) throws IOException {

        ClientController c = new ClientController();

        JSONArray array = c.getInfo();
        System.out.println(array);

    }


}
