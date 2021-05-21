package com.infinitePossibilities;

import com.google.gson.Gson;
import com.infinitePossibilities.entity.BulkInfo;
import com.infinitePossibilities.entity.ProductInfo;
import com.infinitePossibilities.mapper.BulkInfoMapper;
import com.infinitePossibilities.mapper.ProductInfoMapper;
import com.infinitePossibilities.util.ESClient;
import lombok.SneakyThrows;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.ElasticsearchNodesSniffer;
import org.elasticsearch.client.sniff.NodesSniffer;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.InetAddress;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class EsApplicationTests {

    @Autowired
    private BulkInfoMapper bulkInfoMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Test
    @SneakyThrows
    void esCRUD() {

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
//        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))//通讯端口  而不是服务端口
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9301));
        //导入数据
        createT(client);
        //查询
//        get(client);
//        getAll(client);
//        update(client);
//        delete(client);
        client.close();
        System.out.println(client);
        //Add transport addresses and do something with the client...
    }

//    region create
    @SneakyThrows
    private void createT(TransportClient client) {
        IndexResponse response = client.prepareIndex("search", "_doc", "11")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("itemName", "医保局")
                        .field("itemCode", "000111")
                        .field("targetType","个人")
                        .field("num", "10999")
                        .field("tags", new String[]{
                                "yibaoiju", "quji", "geren"
                        })
                        .endObject())
                .get();
        System.out.println(response.getResult());
    }


    //region create
    @SneakyThrows
    private void create(TransportClient client) {
//        List<Product> list = service.list();
        List<ProductInfo> list = new ArrayList<>();
        for (ProductInfo item : list) {
            System.out.println(item.getInputDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            IndexResponse response = client.prepareIndex("product", "_doc", item.getId().toString())
                    .setSource(XContentFactory.jsonBuilder()
                            .startObject()
                            .field("id", item.getId())
                            .field("itemName", item.getItemname())
                            .field("itemCode", item.getItemcode())
                            .field("targetType", item.getTargettype())
                            .field("num", item.getNum())
                            .field("input_date", item.getInputDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .field("tags", item.getTags().split(","))
                            .endObject())
                    .get();
            System.out.println(response.getResult());
        }
    }


    //region get
    /*
     * 功能描述: <br>
     * 〈〉
     * @Param: [client]
     * @Return: void
     */
    @SneakyThrows
    private void get(TransportClient client) {
        GetResponse response = client.prepareGet("search", "_doc", "1").get();
        String index = response.getIndex();//获取索引名称
        String type = response.getType();//获取索引类型
        String id = response.getId();//获取索引id
        System.out.println("index : " + index);
        System.out.println("type : " + type);
        System.out.println("id : " + id);
        System.out.println(response.getSourceAsString());
    }


    //region getAll
    private void getAll(TransportClient client) {
        SearchResponse response = client.prepareSearch("search").setSize(100)
                .get();
        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String res = hit.getSourceAsString();
            System.out.println("res" + res);
        }
    }


    //region update
    @SneakyThrows
    private void update(TransportClient client) {
        UpdateResponse response = client.prepareUpdate("search", "_doc", "11")
                .setDoc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("itemName", "医保局Text")
                        .endObject())
                .get();
        System.out.println(response.getResult());
    }


    //region delete
    @SneakyThrows
    private void delete(TransportClient client) {
        DeleteResponse response = client.prepareDelete("search", "_doc", "11").get();
        System.out.println(response.getResult());
    }


    //region multiSearch
    /*
     * 功能描述: <br>
     * 〈多条件查找〉
     * @Param: []
     * @Return: void
     */
    @Test
    @SneakyThrows
    void multiSearch() {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9301))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9302));

        SearchResponse response = client.prepareSearch("data-group-aggs")
                .setQuery(QueryBuilders.termQuery("itemName.keyword", "交通委"))                 // Query
                .setPostFilter(QueryBuilders.rangeQuery("num").from(0).to(4000))
                .setFrom(0).setSize(3)
                .get();
        SearchHits searchHits = response.getHits();
        SearchHit[] hits = searchHits.getHits();
        for (SearchHit hit : hits) {
            String res = hit.getSourceAsString();
            System.out.println("res" + res);
        }
        client.close();
    }


    //region 聚合查询
    /*
     * 功能描述: <br>
     * 〈多条件查找〉
     * @Param: []
     * @Return: void
     */
    @Test
    @SneakyThrows
    void aggSearch() {
        //region 1->创建客户端连接
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9301))
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9302));
        //endregion

        //region 2->计算并返回聚合分析response对象
//        SearchResponse response = client.prepareSearch("data-group-aggs")
        SearchResponse response = client.prepareSearch("index_product")
                .addAggregation(
                        AggregationBuilders.dateHistogram("group_by_month")
//                                .field("data")
                                .field("inputDate")
                                .calendarInterval(DateHistogramInterval.MONTH)
//                                .dateHistogramInterval(DateHistogramInterval.MONTH)
                                .subAggregation(
                                        AggregationBuilders
                                                .terms("by_tag")
                                                .field("tags.keyword")
                                                .subAggregation(
                                                        AggregationBuilders
                                                                .avg("avg_price")
                                                                .field("num")
                                                )
                                )
                )
                .execute().actionGet();


        //region 3->输出结果信息
        SearchHit[] hits = response.getHits().getHits();
        Map<String, Aggregation> map = response.getAggregations().asMap();
        Aggregation group_by_month = map.get("group_by_month");
        Histogram dates = (Histogram) group_by_month;
        Iterator<Histogram.Bucket> buckets = (Iterator<Histogram.Bucket>) dates.getBuckets().iterator();

        while (buckets.hasNext()) {
            Histogram.Bucket dateBucket = buckets.next();
            System.out.println("\n\n月份：" + dateBucket.getKeyAsString() + "\n计数：" + dateBucket.getDocCount());
            Aggregation group_by_tag = dateBucket.getAggregations().asMap().get("by_tag");
            StringTerms terms = (StringTerms) group_by_tag;
            Iterator<StringTerms.Bucket> tagsBucket = terms.getBuckets().iterator();
            while (tagsBucket.hasNext()) {
                StringTerms.Bucket tagBucket = tagsBucket.next();
                System.out.println("\t标签名称：" + tagBucket.getKey() + "\n\t数量：" + tagBucket.getDocCount());
                Aggregation avg_price = tagBucket.getAggregations().get("avg_price");
                Avg avg = (Avg) avg_price;
                System.out.println("\t平均数量：" + avg.getValue() + "\n");
            }
        }
        //endregion

        client.close();


    }


    //*******************************************************!!!!!!!!!!!!!!!***************************************************************


    @Test
    @SneakyThrows
    public void createIndex() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));

        CreateIndexRequest request = new CreateIndexRequest("test_index");

        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        if (createIndexResponse.isAcknowledged()) {
            System.out.println("创建index成功!");
        } else {
            System.out.println("创建index失败!");
        }

        client.close();
    }

    @Test
    @SneakyThrows
    public void getIndex() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));

        GetIndexRequest request = new GetIndexRequest("se*");
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);
        String[] indices = response.getIndices();
        for (String indexName : indices) {
            System.out.println("index name:" + indexName);
        }
        client.close();
    }

    @Test
    @SneakyThrows
    public void delIndex() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));
        DeleteIndexRequest request = new DeleteIndexRequest("test_index");
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        if (response.isAcknowledged()) {
            System.out.println("删除index成功!");
        } else {
            System.out.println("删除index失败!");
        }
        client.close();
    }

    @Test
    @SneakyThrows
    public void insertData() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));

        List<ProductInfo> list = productInfoMapper.selectAll();

        System.out.println("list : "+list);
        //插入数据，index不存在则自动根据匹配到的template创建。index没必要每天创建一个，如果是为了灵活管理，最低建议每月一个 yyyyMM。
        IndexRequest request = new IndexRequest("index_product");
        //最好不要自定义id 会影响插入速度。
        ProductInfo product = list.get(0);
        Gson gson = new Gson();
        request.id(product.getId().toString());
        request.source(gson.toJson(product)
                , XContentType.JSON);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response);
        client.close();
    }
//    @Test
//    @SneakyThrows
//    public void batchInsertData() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("localhost", 9200, "http"),
//                        new HttpHost("localhost", 9201, "http"),
//                        new HttpHost("localhost", 9202, "http")));
//        //批量插入数据，更新和删除同理
//        BulkRequest request = new BulkRequest("index_product");
//        Gson gson = new Gson();
//        ProductInfo product = new ProductInfo();
//        product.setNum(3999);
//        product.setTags("xioami");
//        for (int i = 0; i < 10; i++) {
//            product.setItemname("name" + i);
//            request.add(new IndexRequest().source(gson.toJson(product)
//                    , XContentType.JSON));
//        }
//        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
//        System.out.println("数量:" + response.getItems().length);
//        client.close();
//    }


    @Test
    @SneakyThrows
    public void batchInsertData() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));
        //查询数据库  批量插入数据，更新和删除同理
        BulkRequest request = new BulkRequest("index_product");
        Gson gson = new Gson();
        List<ProductInfo> lists = productInfoMapper.selectAll();

        for (ProductInfo list: lists) {
            System.out.println(gson.toJson(list));
            request.add(new IndexRequest().source(gson.toJson(list)
                    , XContentType.JSON));
        }

        BulkResponse response = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println("数量:" + response.getItems().length);
        client.close();
    }


    @Test
    @SneakyThrows
    public void getDate(){

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost",9200,"http"),
                        new HttpHost("localhost",9200,"http"),
                        new HttpHost("localhost",9200,"http")));

        BulkRequest request = new BulkRequest("index_product");
        List<ProductInfo> lists = productInfoMapper.selectAll();
        Gson gson = new Gson();
        for (ProductInfo list: lists) {

                //数据库时间转换
                Date date = new Date(list.getInputDate().getTime());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String sd = sdf.format(date);

                String gsons = gson.toJson(list);
                JSONObject json = new JSONObject(gsons);
                json.put("inputDate",sd);
                request.add(new IndexRequest().source(json.toString()
                        ,XContentType.JSON));
        }

        BulkResponse responses = client.bulk(request,RequestOptions.DEFAULT);
        System.out.println("数量 ： "+responses.getItems().length);
        client.close();
    }



    //根据id查询
    @Test
    @SneakyThrows
    public void getById() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));
        //注意 这里查询使用的是别名。
        GetRequest request = new GetRequest("index_product", "mQYKzncBSw_3e2MkFkmH");

        //只查询特定字段。如果需要查询所有字段则不设置该项。
        String[] includes = {"itemname", "itemcode","inputDate"};
        String[] excludes = {"desc"};
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response);
        client.close();

    }

    @Test
    public void delById() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));
        DeleteRequest request = new DeleteRequest("ac-new", "6");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
        client.close();
    }

    @Test
    public void multiGetById() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));
        //多个结果  根据id查询
        MultiGetRequest request = new MultiGetRequest();

        request.add("index_product", "ewbmzXcBSw_3e2MkIkk-");
        request.add("index_product", "jQbrzXcBSw_3e2Mkb0m6");
        //两种写法
//        request.add(new MultiGetRequest.Item(
//                "test_index",
//                "ewbmzXcBSw_3e2MkIkk-"));
        MultiGetResponse response = client.mget(request, RequestOptions.DEFAULT);
        for (MultiGetItemResponse itemResponse : response) {
            System.out.println(itemResponse.getResponse().getSourceAsString());
        }
        client.close();
    }

    @Test
    public void updateByQuery() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http"),
                        new HttpHost("localhost", 9202, "http")));
        UpdateByQueryRequest request = new UpdateByQueryRequest("index_product");
        //默认情况下，版本冲突会中止 UpdateByQueryRequest 进程，但是你可以用以下命令来代替
        //设置版本冲突继续
//        request.setConflicts("proceed");

        //设置更新条件   加 keyword 可以精确匹配，不会被分词
        request.setQuery(QueryBuilders.matchQuery("itemname.keyword","交通委"));
//        //限制更新条数
        request.setMaxDocs(8);
        request.setScript(
                new Script(ScriptType.INLINE, "painless", "ctx._source.itemname+='#';", Collections.emptyMap()));
        BulkByScrollResponse response = client.updateByQuery(request, RequestOptions.DEFAULT);
        System.out.println(response);
        client.close();
    }


    //********************************************************************************************************************


    @Test
    @SneakyThrows
    public void getHighLevelClient(){

        RestHighLevelClient client = ESClient.getEsClient().getHighLevelClient();

        ESClient.getEsClient().closeClient();
    }

    //嗅探器
    @Test
    public void sniffer() throws IOException {

        // region 监听器
        SniffOnFailureListener sniffOnFailureListener = new SniffOnFailureListener();

        //获取客户端
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost",9200,"http"),
                new HttpHost("localhost",9201,"http"),
                new HttpHost("localhost",9202,"http"))
                .setFailureListener(sniffOnFailureListener)
                .build();

        //设置 https
        NodesSniffer nodesSniffer = new ElasticsearchNodesSniffer(
                restClient,
                ElasticsearchNodesSniffer.DEFAULT_SNIFF_REQUEST_TIMEOUT,
                ElasticsearchNodesSniffer.Scheme.HTTPS
        );

        //为 restClient 绑定 嗅探器
        Sniffer sniffer = Sniffer.builder(restClient)
                .setSniffIntervalMillis(5000)  //设置连续两次普通嗅探执行之间的间隔（以毫秒为单位）
                .setSniffAfterFailureDelayMillis(30000)  //设置失败后计划执行嗅探的延迟（以毫秒为单位）
                .setNodesSniffer(nodesSniffer)
                .build();

        sniffOnFailureListener.setSniffer(sniffer);

        //先关嗅探器, 后关客户端
        sniffer.close();
        restClient.close();
    }

    //查看节点信息
    @Test
    @SneakyThrows
    public void snifferT(){
        RestHighLevelClient client = ESClient.getEsClient().getHighLevelClient();

        Iterator<Node> nodes = client.getLowLevelClient().getNodes().iterator();
        while (nodes.hasNext()){
            Node node = nodes.next();
            System.out.println("初始化节点 : " + node);
        }

        Thread.sleep(60000);
        System.out.println("准备二次扫描:");
        nodes = client.getLowLevelClient().getNodes().iterator();
        while (nodes.hasNext()){
            Node node = nodes.next();
            System.out.println("二次扫描 : "+node);
        }

        Thread.sleep(60000);
        System.out.println("准备三次扫描:");
        nodes = client.getLowLevelClient().getNodes().iterator();
        while (nodes.hasNext()){
            Node node = nodes.next();
            System.out.println("三次扫描 : "+node);
        }
        ESClient.getEsClient().closeClient();

    }

    @Test
    @SneakyThrows
    public void  bulkInit(){
        RestHighLevelClient client = ESClient.getEsClient().getHighLevelClient();
        GetIndexRequest request = new GetIndexRequest("bulk_index");
        Boolean exists = client.indices().exists(request,RequestOptions.DEFAULT);

        if(!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("bulk_index");
            createIndexRequest.settings(Settings.builder()
                    .put("index.number_of_shards", 3) //3个 主分片
                    .put("index.number_of_replicas", 2)); //2个副本 （每个主分片对应2个副本）

            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
            int i = createIndexResponse.hashCode();
            boolean acknowledged = createIndexResponse.isAcknowledged();
            System.out.println("创建索引 ACK : "+acknowledged+"   索引 code : "+i);
        }

            List<BulkInfo> list = bulkInfoMapper.selectAll();

            BulkRequest bulkRequest = new BulkRequest("bulk_index");
            Gson gson = new Gson();
            for (int i=0;i<list.size();i++){

                //数据库时间转换
                Date date = new Date(list.get(i).getInputDate().getTime());
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String sd = sdf.format(date);
                //替换时间字段
                String gsons = gson.toJson(list.get(i));
                JSONObject json = new JSONObject(gsons);
                json.put("inputDate",sd);

                bulkRequest.add(new IndexRequest().id(Integer.toString(i)).source(json.toString(),XContentType.JSON));
            }

            BulkResponse response = client.bulk(bulkRequest,RequestOptions.DEFAULT);
            System.out.println("插入条数 : "+response.getItems().length);

           ESClient.getEsClient().closeClient();


    }



}
