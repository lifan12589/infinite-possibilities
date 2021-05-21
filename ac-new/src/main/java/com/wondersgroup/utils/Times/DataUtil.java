package com.wondersgroup.utils.Times;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DataUtil {

	public static Date getDate() throws ParseException {

		Date time = new Date();// 获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化当前日期
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");// 格式化当前日期
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy.MM.dd");// 格式化当前日期
		Date nowDate = sdf.parse(sdf.format(time));
		return nowDate;

	}

	public static String getDateString(String type) throws ParseException {

		Date time = new Date();// 获取当前日期
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");// 格式化当前日期
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");// 格式化当前日期
		String dataString="null";
		if("1".equals(type)) {
			dataString=sdf1.format(time);
		}else if("2".equals(type)) {
			dataString=sdf2.format(time);
		}else {
			dataString="请输入正确参数（1或者2）";
		}
		return dataString;

	}
	
	public static String nowTime(){
		
		Calendar  cal=Calendar.getInstance();
		 cal.setTime(new Date());
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int date=cal.get(Calendar.DATE);
		int hour=cal.get(Calendar.HOUR);
		int minute=cal.get(Calendar.MINUTE);
		int second=cal.get(Calendar.SECOND);
		int milliSecond=cal.get(Calendar.MILLISECOND);
		String nowTime = year+"年"+month+"月"+date+"日 "+hour+":"+minute+":"+second+":"+milliSecond;
		System.out.println(nowTime);
		return null;
		
		
	}
	
	
	public static void main(String[] args) throws ParseException {
//		Date time = new Date();// 获取当前日期
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化当前日期
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");// 格式化当前日期
//		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy.MM.dd");// 格式化当前日期
//		String nowDate = sdf.format(time).toString();
//		System.out.println(nowDate);

		Calendar  cal=Calendar.getInstance();
		cal.setTime(new Date());
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int date=cal.get(Calendar.DATE);
		String start = year-3+"-"+month+"-"+date;
		String nowTime = year+"-"+month+"-"+date;
		System.out.println(start+"  /  "+nowTime);


		
	}
}
