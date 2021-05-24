package com.infinitePossibilities.utils.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Maps {

	public static void main(String[] args) {
      Map<String,String> map=new HashMap<String,String>();
      map.put("1", "value1");
      map.put("2", "value2");
      map.put("3", "value3");
    //第一种：普遍使用，二次取值
      System.out.println("通过Map.keySet遍历key和value");
      for(String key:map.keySet())
      {
          String value=map.get(key);
          System.out.println(key+":"+value);
      }
      System.out.println("-------------------------");
      //第二种：
      System.out.println("通过Map.entrySet使用iterator遍历key和value");
      Iterator<Map.Entry<String,String>> it=map.entrySet().iterator();
      while(it.hasNext()) {
          Map.Entry<String, String> entry=it.next();
          String key=entry.getKey();
          String value=entry.getValue();
          System.out.println(key+":"+value);
      }
      System.out.println("-------------------------");
      //第三种：推荐，尤其是容量大时
      System.out.println("通过Map.entrySet遍历key和value");
      for(Map.Entry<String, String> entry:map.entrySet()) {
          String key=entry.getKey();
          String value=entry.getValue();
          System.out.println(key+":"+value);
      }
      System.out.println("-------------------------");
      //第四种 
      System.out.println("通过map.values()遍历所有的value,但不能遍历key");
      for(String value:map.values()) {
          System.out.println("value="+value);
      }
    }
	
	
	
}
