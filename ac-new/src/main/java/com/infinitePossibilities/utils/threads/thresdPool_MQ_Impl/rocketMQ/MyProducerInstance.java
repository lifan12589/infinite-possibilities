package com.infinitePossibilities.utils.threads.thresdPool_MQ_Impl.rocketMQ;

/**
 * 解决重复创建 生产者  group冲突  问题：
 * The producer group[threadPool_producer_Group] has been created before, specify another name please.
 *调用：
 * MQProducer producer = MyProducerInstance.getProducerInstance().getInstance(namesrvAddr,groupName,"");

 * 需要操作不同的 instanceName 下的 producer 时，添加上 instanceName 参数 即可（方法第三个参数）。
 */
//public class MyProducerInstance {
//
//    public static final String APPENDER_TYPE = "APPENDER_TYPE";
//
//    public static final String LOG4J_APPENDER = "LOG4J_APPENDER";
//
//    public static final String LOG4J2_APPENDER = "LOG4J2_APPENDER";
//
//    public static final String LOGBACK_APPENDER = "LOGBACK_APPENDER";
//
//    public static final String DEFAULT_GROUP = "rocketmq_appender";
//
//    private ConcurrentHashMap<String, MQProducer> producerMap = new ConcurrentHashMap<String, MQProducer>();
//
//    private static MyProducerInstance instance = new MyProducerInstance();
//
//    public static MyProducerInstance getProducerInstance() {
//        return instance;
//    }
//
//    private String genKey(String nameServerAddress, String group,String instanceName) {
//        return nameServerAddress + "_" + group + "_" + instanceName;
//    }
//
//    public MQProducer getInstance(String nameServerAddress, String group,String instanceName) throws MQClientException {
//        if (StringUtils.isBlank(group)) {
//            group = DEFAULT_GROUP;
//        }
//        if (StringUtils.isBlank(instanceName)) {
//            instanceName = "DEFAULT";
//        }
//
//        String genKey = genKey(nameServerAddress, group, instanceName);
//        MQProducer p = getProducerInstance().producerMap.get(genKey);
//        if (p != null) {
//            return p;
//        }
//
//        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(group);
//        defaultMQProducer.setNamesrvAddr(nameServerAddress);
//        defaultMQProducer.setInstanceName(instanceName);
//
//        MQProducer beforeProducer = null;
//        beforeProducer = getProducerInstance().producerMap.putIfAbsent(genKey, defaultMQProducer);
//        if (beforeProducer != null) {
//            return beforeProducer;
//        }
//        defaultMQProducer.start();
//        return defaultMQProducer;
//    }
//
//    public void removeAndClose(String nameServerAddress, String group, String instanceName) {
//        if (group == null) {
//            group = DEFAULT_GROUP;
//        }
//
//        if (StringUtils.isBlank(instanceName)) {
//            instanceName = "DEFAULT";
//        }
//
//        String genKey = genKey(nameServerAddress, group,instanceName);
//        MQProducer producer = getProducerInstance().producerMap.remove(genKey);
//
//        if (producer != null) {
//            producer.shutdown();
//        }
//    }
//
//    public void closeAll() {
//        Set<Map.Entry<String, MQProducer>> entries = getProducerInstance().producerMap.entrySet();
//        for (Map.Entry<String, MQProducer> entry : entries) {
//            getProducerInstance().producerMap.remove(entry.getKey());
//            entry.getValue().shutdown();
//        }
//    }
//}
