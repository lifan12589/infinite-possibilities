//package com.wondersgroup.utils.threads.thresdPool_MQ_Impl;
//
//import com.alibaba.fastjson.JSONObject;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        Timestamp saveTime=new Timestamp(new Date().getTime());
//        JSONObject json = new  JSONObject();
//        json.put("time", saveTime);
//        System.out.println(json.getString("time"));
//
//
//        String mass = json.toString();
//        JSONObject jsons = JSONObject.parseObject(mass);
//        String times = jsons.getString("time");
//        System.out.println(times);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(times))));      // 时间戳转换成时间
//        System.out.println("格式化结果：" + sd);
//    }
//
//
//}
