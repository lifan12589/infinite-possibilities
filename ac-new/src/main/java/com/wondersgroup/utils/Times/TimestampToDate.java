package com.wondersgroup.utils.Times;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampToDate {

	public static void main(String[] args) {

//	        String timeStamps = "1595001600000"; 
//	        Long timeStamp = Long.parseLong(timeStamps);
			new java.util.Date().getTime();//时间戳
			Timestamp saveTime=new Timestamp(new java.util.Date().getTime());//SQL时间
			Timestamp saveTime2=new Timestamp(new java.util.Date().getTime()+60000);//SQL时间+60秒



			Long timestamp = System.currentTimeMillis();  //获取当前时间戳
		 	System.out.println(timestamp);
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timestamp))));      // 时间戳转换成时间
	        System.out.println("格式化结果：" + sd);
	 
	        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");
	        String sd2 = sdf2.format(new Date(Long.parseLong(String.valueOf(timestamp))));
	        System.out.println("格式化结果：" + sd2);
	}

	
	
}
