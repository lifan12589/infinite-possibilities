//package com.wondersgroup.utils.threads.thresdPool_MQ_Impl.rocketMQ;
//
//import java.lang.management.ManagementFactory;
//import java.lang.management.RuntimeMXBean;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * 提供唯一instance name:
// * 解决重复创建 生产者  group冲突  问题：
// * The producer group[threadPool_producer_Group] has been created before, specify another name please.
// * 调用：
// * consumer.setInstanceName(GetInstanceName.getRocketMqUniqeInstanceName());
// */
//public class GetInstanceName {
//
//    private static AtomicInteger index = new AtomicInteger();
//    public static int getPid() {
//        String info = getRunTimeInfo();
//        int pid = (new Random()).nextInt();
//        int index = info.indexOf("@");
//        if(index > 0) {
//            pid = Integer.parseInt(info.substring(0, index));
//        }
//        return pid;
//    }
//    public static String getRunTimeInfo() {
//        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
//        String info = runtime.getName();
//        return info;
//    }
//    public static String getRocketMqUniqeInstanceName() {
//        return "pid" + getPid() + "_index" + index.incrementAndGet();
//    }
//
//
//}
