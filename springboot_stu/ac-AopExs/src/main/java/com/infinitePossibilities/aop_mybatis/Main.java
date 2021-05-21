package com.infinitePossibilities.aop_mybatis;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(Calendar.getInstance().getTime());
        System.out.println(time);
        Calendar Time = Calendar.getInstance();
        int year = Time.get(Calendar.YEAR);
        int month = Time.get(Calendar.MONTH) + 1;
        int day = Time.get(Calendar.DAY_OF_MONTH);
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);


    }




}
