package com.infinitePossibilities;

import net.minidev.json.JSONObject;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Map;

public class EsUtil {


    //生成批量处理对象
    private static BulkRequest bulkRequest = new BulkRequest();

    /**
     * 添加数据到ES
     * @param restHighLevelClient
     * @param indexName
     * @param typeName
     * @param indexId
     * @param json
     */
    public static void add(RestHighLevelClient restHighLevelClient, String indexName,
                           String typeName, String indexId, Map<String,Override> json){

        IndexRequest indexRequest = new IndexRequest(indexName,typeName,indexId);

        indexRequest.source(new JSONObject(json).toString(), XContentType.JSON);

        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断索引名是否存在
     * @param restHighLevelClient
     * @param indexName
     * @return
     */
    public static boolean existsIndex(RestHighLevelClient restHighLevelClient,String indexName){
        try {
            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
            return exists;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断文档是否存在
     * @param restHighLevelClient
     * @param indexName
     * @param typeName
     * @param indexId
     * @return
     */
    public static boolean isExist(RestHighLevelClient restHighLevelClient,String indexName,
                                  String typeName, String indexId) throws IOException {

            GetRequest getRequest = new GetRequest(indexName,typeName,indexId);
            //同步判断
            boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);

            //异步判断
            ActionListener<Boolean> listener = new ActionListener<Boolean>() {

            @Override
            public void onResponse(Boolean aBoolean) {

                if(exists){
                    System.out.println("文档已存在！");
                }else{
                    System.out.println("文档不存在！");
                }
            }

            @Override
            public void onFailure(Exception e) {

                System.out.println("发生异常 : "+e.getMessage());
            }
        };

        return false;
    }

    /**
     * 删除文档
     * @param restHighLevelClient
     * @param indexName
     * @param typeName
     * @param indexId
     */
    public static void deleteDocument(RestHighLevelClient restHighLevelClient,String indexName,
                                 String typeName,String indexId) throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest(indexName,typeName,indexId);

        deleteRequest.timeout(TimeValue.timeValueMinutes(2));

        //同步删除
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);

        //异步删除
        ActionListener<DeleteResponse> listener = new ActionListener<DeleteResponse>() {

            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                System.out.println("删除成功！");
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("删除失败！");
            }
        };
    }

    /**
     * 批量增加数据的方法
     * @param restHighLevelClient
     * @param indexName
     * @param typeName
     * @param row_key
     * @param map
     * @throws IOException
     */
    public void bulkadd(RestHighLevelClient restHighLevelClient,String indexName, String typeName,
                        String row_key,Map<String,Object> map) throws IOException {

        try {
            //得到某一行的数据 , 开启,并封装成对象
            IndexRequest indexRequest = new IndexRequest(indexName,typeName,row_key);
            indexRequest.source(new JSONObject(map).toString(),XContentType.JSON);

            if(bulkRequest.numberOfActions()!=0 && (bulkRequest.numberOfActions()>100)){

                try {
                    bulkRequest(restHighLevelClient);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bulkRequest.add(indexRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bulkRequest(restHighLevelClient);
        }


    }

    /**
     * 批量具体的执行方法
     * @param restHighLevelClient
     * @throws IOException
     */
    private void bulkRequest(RestHighLevelClient restHighLevelClient) throws IOException {

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);

        if(bulkResponse.hasFailures()){
            System.out.println("失败！");
        }else{
            System.out.println("成功！");
            bulkRequest = new BulkRequest();
        }

    }



}

