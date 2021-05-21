package com.wondersgroup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StringToMapUtil {
	
	public static Object getValue(String param) {
        Map<String, Object> map = new HashMap<String, Object>();
        String str = "";
        String key = "";
        Object value = "";
        char[] charList = param.toCharArray();
        boolean valueBegin = false;
        for (int i = 0; i < charList.length; i++) {
            char c = charList[i];
            if (c == '{') {
                if (valueBegin == true) {
                    value = getValue(param.substring(i, param.length()));
                    i = param.indexOf('}', i) + 1;
                    map.put(key, value);
                }
            } else if (c == '=') {
                valueBegin = true;
                key = str;
                str = "";
            } else if (c == ',') {
                valueBegin = false;
                value = str;
                str = "";
                map.put(key, value);
            } else if (c == '}') {
                if (str != "") {
                    value = str;
                }
                map.put(key, value);
                return map;
            } else if (c != ' ') {
                str += c;
            }
        }
        return map;
    }
	

	public static void main(String[] args) {
		Map<String, String> map=new HashMap<String, String>();
//		         map.put("张三1", "男");
//		         map.put("张三2", "女");
//		         map.containsKey("张三1");
//		      System.out.println(map.containsKey("张三1"));   
//		       System.out.println(map.containsValue("女"));  
		       
		         
		         
		         
		         
		       
		        //第一种遍历map的方法，通过加强for循环map.keySet()，然后通过键key获取到value值
//		         for(String s:map.keySet()){
//		            System.out.println("key : "+s+" value : "+map.get(s));
//		         }
//		        
//		         
//		         for(String s1:map.keySet()){//遍历map的键
//		        	              System.out.println("键key ："+s1);
//		        	          }
//		        	          for(String s2:map.values()){//遍历map的值
//		        	              System.out.println("值value ："+s2);
//		        	          }
//		         
//		        	          for(Map.Entry<String, String> entry : map.entrySet()){
//		        	        	               System.out.println("键 key ："+entry.getKey()+" 值value ："+entry.getValue());
//		        	        	           }
//		         
//		        	          Iterator<Map.Entry<String, String>> it=map.entrySet().iterator();
//		        	                   while(it.hasNext()){
//		        	                	   Map.Entry<String, String> entry=it.next();
//		        	                       System.out.println("键key ："+entry.getKey()+" value ："+entry.getValue());
//		        	                   }
		         
//		         Date date=new Date();
//		         SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//		         sdf.format(date);
//		         System.out.println(sdf.format(date));
//		         System.out.println(System.currentTimeMillis()); // 毫秒
		         
		         
//		         
//		        Map<String, Object> maps =  (Map<String, Object>) getValue("1111,222,333,444,555");
//		         
//		         System.out.println(maps);
//		         System.exit(0);
//		         
		        

	}

}
