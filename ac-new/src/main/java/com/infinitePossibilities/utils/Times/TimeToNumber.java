package com.infinitePossibilities.utils.Times;


import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeToNumber {
    public static void main(String[] args) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);//加30天
        Date date = c.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = date2.format(date); 
        System.out.println(dateStr);
        Date d = new Date();
        System.out.println(d.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat.format(d));
    }
    /**
      * 将日期的数字格式转化为中文〇一二三四五六七八九十
     */
    public static String dateCoverToChinese(String dateStr, boolean yearCheck,boolean monthCheck,boolean dayCheck,boolean allcheck) throws ParseException {
        if (StringUtils.isBlank(dateStr) || !isValidDate(dateStr)) {
            return null;
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        String[] NUMBERS = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九","十" };
        StringBuffer chinese = new StringBuffer();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (yearCheck) {
            // 处理年
            int yearY = c.get(Calendar.YEAR);
            String year = String.valueOf(yearY);
            for (int i = 0; i < year.length(); i++) {
                chinese.append(NUMBERS[Integer.valueOf(String.valueOf(year.charAt(i)))]);
            }
            if (allcheck){
                chinese.append("年");
            }
        }
        if (monthCheck) {
            // 处理月
            int month = c.get(Calendar.MONTH) + 1;
            if (month < 10) {
                chinese.append(NUMBERS[month]);
            } else if (month == 10) {
                chinese.append(NUMBERS[10]);
            } else {
                chinese.append(NUMBERS[10]).append(NUMBERS[month % 10]);
            }
            if (allcheck) {
                chinese.append("月");
            }
        }
        if (dayCheck) {
            // 处理日
            int day = c.get(Calendar.DAY_OF_MONTH);
            if (day < 10) {
                chinese.append(NUMBERS[day]);
            } else if (day == 10) {
                chinese.append(NUMBERS[10]);
            } else if (day < 20) {
                chinese.append(NUMBERS[10]).append(NUMBERS[day % 10]);
            } else if (day == 20) {
                chinese.append(NUMBERS[2]).append(NUMBERS[10]);
            } else if (day < 30) {
                chinese.append(NUMBERS[2]).append(NUMBERS[10]).append(
                        NUMBERS[day % 20]);
            } else if (day == 30) {
                chinese.append(NUMBERS[3]).append(NUMBERS[10]);
            } else {
                chinese.append(NUMBERS[3]).append(NUMBERS[10]).append(
                        NUMBERS[day % 30]);
            }
            if (allcheck) {
                chinese.append("日");
            }
        }
        return chinese.toString();
    }
    //校验字符串能否转换成日期
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
