package com.infinitePossibilities.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.log.Log;

@	Controller
public class GetEncodingController {

	@RequestMapping("/yb/getString.do")
	public void getString(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		HttpReqRes httpReqRes = new HttpReqRes(request, response);
		
		
		String str = httpReqRes.getParameter("data");
		Log.info("data11111111111111  :  "+str);
		
		Log.info("getEncoding--ISO-UTF-8：" + new String(str.getBytes("ISO-8859-1"), "UTF-8"));
		Log.info("--GB2312 ：" + new String(str.getBytes(), "GB2312"));
		Log.info("--ISO-8859-1 ：" + new String(str.getBytes(), "ISO-8859-1"));
		Log.info("--UTF-8 ：" + new String(str.getBytes(), "UTF-8"));
		Log.info("--GBK ：" + new String(str.getBytes(), "GBK"));
		
		Log.info("--GB2312 ---2：" + new String(str.getBytes("GB2312")));
		Log.info("--ISO-8859-1 ---2：" + new String(str.getBytes("ISO-8859-1")));
		Log.info("--UTF-8--2：" + new String(str.getBytes("UTF-8")));
		Log.info("--GBK--2 ：" + new String(str.getBytes("GBK")));
		
		Log.info("UTF-8--GB2312 ：" + new String(str.getBytes("UTF-8"), "GB2312"));
		Log.info("UTF-8--ISO-8859-1 ：" + new String(str.getBytes("UTF-8"), "ISO-8859-1"));
		Log.info("UTF-8--UTF-8 ：" + new String(str.getBytes("UTF-8"), "UTF-8"));
		Log.info("UTF-8--GBK ：" + new String(str.getBytes("UTF-8"), "GBK"));
		
		Log.info("GB2312--UTF-8 ：" + new String(str.getBytes("GB2312"), "UTF-8"));
		Log.info("GB2312--ISO-8859-1 ：" + new String(str.getBytes("GB2312"), "ISO-8859-1"));
		Log.info("GB2312--GBK ：" + new String(str.getBytes("GB2312"), "GBK"));
		Log.info("GB2312--GB2312 ：" + new String(str.getBytes("GB2312"), "GB2312"));
		
		Log.info("ISO-8859-1--GBK ：" + new String(str.getBytes("ISO-8859-1"), "GBK"));
		Log.info("ISO-8859-1--UTF-8 ：" + new String(str.getBytes("ISO-8859-1"), "UTF-8"));
		Log.info("ISO-8859-1--GB2312 ：" + new String(str.getBytes("ISO-8859-1"), "GB2312"));
		Log.info("ISO-8859-1--ISO-8859-1 ：" + new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));
		
		Log.info("GBK--GB2312：" + new String(str.getBytes("GBK"), "GB2312"));
		Log.info("GBK--ISO-8859-1：" + new String(str.getBytes("GBK"), "ISO-8859-1"));
		Log.info("GBK--UTF-8：" + new String(str.getBytes("GBK"), "UTF-8"));
		Log.info("GBK--GBK：" + new String(str.getBytes("GBK"), "GBK"));
		
		
		
		String data = "data="+str;
		
//		JSONObject json = getData("http://localhost:8090/ac-product-net/yb/getString2.do",new String( data.getBytes("UTF-8")));

		String fhStr1 =new String(str.getBytes("ISO-8859-1"), "UTF-8");
		String fhStr2 = new String(str.getBytes("UTF-8"));
		
		httpReqRes.writeJson(fhStr1+"    ---    "+fhStr2+"       ----json---   "+json); 
		
	}
	
	
	
	@RequestMapping("/yb/getbyte.do")
	public void getByte(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		HttpReqRes httpReqRes = new HttpReqRes(request, response);
		
		
		String str = httpReqRes.getParameter("data");
		Log.info("data11111111111111  :  "+str);
		
		Log.info("getEncoding--ISO-UTF-8：" + new String(str.getBytes("ISO-8859-1"), "UTF-8"));

		Log.info("--GB2312 ：" + new String(str.getBytes(), "GB2312"));
		Log.info("--ISO-8859-1 ：" + new String(str.getBytes(), "ISO-8859-1"));
		Log.info("--UTF-8 ：" + new String(str.getBytes(), "UTF-8"));
		Log.info("--GBK ：" + new String(str.getBytes(), "GBK"));
		
		Log.info("--GB2312 ---2：" + new String(str.getBytes("GB2312")));
		Log.info("--ISO-8859-1 ---2：" + new String(str.getBytes("ISO-8859-1")));
		Log.info("--UTF-8--2：" + new String(str.getBytes("UTF-8")));
		Log.info("--GBK--2 ：" + new String(str.getBytes("GBK")));
		
		Log.info("UTF-8--GB2312 ：" + new String(str.getBytes("UTF-8"), "GB2312"));
		Log.info("UTF-8--ISO-8859-1 ：" + new String(str.getBytes("UTF-8"), "ISO-8859-1"));
		Log.info("UTF-8--UTF-8 ：" + new String(str.getBytes("UTF-8"), "UTF-8"));
		Log.info("UTF-8--GBK ：" + new String(str.getBytes("UTF-8"), "GBK"));
		
		Log.info("GB2312--UTF-8 ：" + new String(str.getBytes("GB2312"), "UTF-8"));
		Log.info("GB2312--ISO-8859-1 ：" + new String(str.getBytes("GB2312"), "ISO-8859-1"));
		Log.info("GB2312--GBK ：" + new String(str.getBytes("GB2312"), "GBK"));
		Log.info("GB2312--GB2312 ：" + new String(str.getBytes("GB2312"), "GB2312"));
		
		Log.info("ISO-8859-1--GBK ：" + new String(str.getBytes("ISO-8859-1"), "GBK"));
		Log.info("ISO-8859-1--UTF-8 ：" + new String(str.getBytes("ISO-8859-1"), "UTF-8"));
		Log.info("ISO-8859-1--GB2312 ：" + new String(str.getBytes("ISO-8859-1"), "GB2312"));
		Log.info("ISO-8859-1--ISO-8859-1 ：" + new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));
		
		Log.info("GBK--GB2312：" + new String(str.getBytes("GBK"), "GB2312"));
		Log.info("GBK--ISO-8859-1：" + new String(str.getBytes("GBK"), "ISO-8859-1"));
		Log.info("GBK--UTF-8：" + new String(str.getBytes("GBK"), "UTF-8"));
		Log.info("GBK--GBK：" + new String(str.getBytes("GBK"), "GBK"));
		
		
//		JSONObject imp = sendBytes("http://localhost:8090/ac-product-net/yb/getByte2.do", str.getBytes("UTF-8"));
//		byte[] bytes = new byte[0];
//		bytes = new byte[imp.available()];
//		imp.read(bytes);
//		String fhData1 = new String(bytes);
		String fhData1 = imp.toString();
		String fhData2 = new String(fhData1.getBytes("UTF-8"));
		
		String fhStr1 =new String(str.getBytes("ISO-8859-1"), "UTF-8");
		String fhStr2 = new String(str.getBytes("UTF-8"));
		
		httpReqRes.writeJson(fhStr1+"    ---    "+fhStr2+"       ----fhData1---   "+fhData1+"       ----fhData2---   "+fhData2); 
		
	}
	
	
	
	public static JSONObject getData(String url,String data) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			httpPost.setEntity(new StringEntity(data));
			HttpResponse response = httpClient.execute(httpPost);
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils
					.toString(response.getEntity()));
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//发送byte数组
	public static JSONObject sendBytes(String url,byte[] data) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		try {
			ByteArrayEntity arrayEntity = new ByteArrayEntity(data);
			httpPost.setEntity(arrayEntity);
			HttpResponse response = httpClient.execute(httpPost);
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils
					.toString(response.getEntity()));
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static InputStream sendByte(String url, byte[] PostData) {
//        URL u = null;
//        HttpURLConnection con = null;
//        InputStream inputStream = null;
//        //尝试发送请求
//        try {
//            u = new URL(url);
//            con = (HttpURLConnection) u.openConnection();
//            con.setRequestMethod("POST");
//            con.setDoOutput(true);
//            con.setDoInput(true);
//            con.setUseCaches(false);
//            con.setRequestProperty("Content-Type", "binary/octet-stream");
//            OutputStream outStream = con.getOutputStream();
//            outStream.write(PostData);
//            outStream.flush();
//            outStream.close();
//            //读取返回内容
//            inputStream = con.getInputStream();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (con != null) {
//            con.disconnect();
//        }
//        return inputStream;
//    }
//}
	
	
	
	
}
