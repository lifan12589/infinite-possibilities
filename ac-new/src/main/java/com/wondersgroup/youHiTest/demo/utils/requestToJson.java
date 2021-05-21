package com.wondersgroup.youHiTest.demo.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class requestToJson {
	
	
	
	/**
     * 获取 request 中 json 字符串的内容
     * 
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
        	String str = new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
        	String deStr = URLDecoder.decode(str);
        	return getRequestGetStr(deStr);
        // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**
     * 获取Get请求的 内容
     * @param str
     * @return
     */
    public static String getRequestGetStr(String str){
    	Map map = new HashMap<>();
		String strs = str.replaceAll("&", ",");
		String st = strs.replaceAll("=", ":");
		System.out.println(st);
		String[] param = st.split(",");
        for (int i = 0; i < param.length; i++) {
            int index = param[i].indexOf(':');
            map.put(param[i].substring(0,index), param[i].substring((index + 1)));
        }
        String sts = JSONObject.toJSONString(map);
		return sts;
    }

	/**
	 * 获取POST请求的 内容
	 * @param request
	 * @return
	 * @throws IOException
	 */
	 public static String getRequestPostStr(HttpServletRequest request)
	            throws IOException {
	        byte buffer[] = getRequestPostBytes(request);
	        String charEncoding = request.getCharacterEncoding();
	        System.out.println(charEncoding);
	        if (charEncoding == null) {
	            charEncoding = "UTF-8";
	        }
	        return new String(buffer, charEncoding);
	    }

	 /**
	  * 获取POST请求的 byte 数组
	  * @param request
	  * @return
	  * @throws IOException
	  */
	 public static byte[] getRequestPostBytes(HttpServletRequest request)
	            throws IOException {
	        int contentLength = request.getContentLength();
	        if(contentLength<0){
	            return null;
	        }
	        byte buffer[] = new byte[contentLength];
	        for (int i = 0; i < contentLength;) {
	            int readlen = request.getInputStream().read(buffer, i,
	                    contentLength - i);
	            if (readlen == -1) {
	                break;
	            }
	            i += readlen;
	        }
	        System.out.println(buffer);
	        return buffer;
	    }

	 
	 /**
	  * JSONArray 根据name去重复
	  * @param array
	  */
	 public static JSONArray delRepeatIndexid(JSONArray array) {
	     JSONArray arrayTemp = new JSONArray();
	     int num = 0;
	     for(int i = 0;i < array.size();i++){
	         if(num==0){
	             arrayTemp.add(array.get(i));
	         }else{
	             int numJ = 0;
	             for(int j = 0;j < arrayTemp.size(); j++){
	                 JSONObject newJsonObjectI = (JSONObject)array.get(i);
	                 JSONObject newJsonObjectJ = (JSONObject)arrayTemp.get(j);
	                 String index_idI = newJsonObjectI.get("code").toString();
	                 String valueI = newJsonObjectI.get("name").toString();
	                 String index_idJ = newJsonObjectJ.get("code").toString();
	                 if(index_idI.equals(index_idJ)){
	                     arrayTemp.remove(j);
	                     JSONObject newObject = new JSONObject();
	                     newObject.put("code", index_idI);
	                     newObject.put("name", valueI);
	                     arrayTemp.add(newObject);
	                     break;
	                 }
	                 numJ++;
	             }
	             if(numJ-1 == arrayTemp.size()-1){
	                 arrayTemp.add(array.get(i));
	             }
	         }
	         num++;
	     }
	     return arrayTemp;
	 }
	 
	 
	 
	public static void main(String[] args) {
		
//		SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date time = new Date();// 获取当前日期
//		String nowDate = nowTime.format(time);
//		System.out.println(nowDate);
//		Timestamp  ts=new Timestamp(new Date().getTime());
//		System.out.println(ts);
		
		String sstr = "sheng=上海市&shi=上海";
		
		
		
	}
	
}
