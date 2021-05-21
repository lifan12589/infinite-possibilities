package com.wondersgroup.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;

@Controller
public class XgfyController {

	
	@RequestMapping("/wjwktjc.do")
	public void wjwktjc(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		
//		String data = request.getParameter("data");
//		JSONObject json =JSONObject.parseObject(data);
		
		JSONObject json =new JSONObject();
		json.put("sid", "84a1e3cd06a841d0bcfadd7075da1a53");
		json.put("xm", "测试1");
		json.put("zjhm", "112233");
		
		String url = "http://10.83.68.161:80/dataex/api/wjwktjc";
		JSONObject responseJson = getUserInfo(url,json);
		System.out.println(responseJson);
		response.getWriter().print(responseJson.toString());
	}
	
	@RequestMapping("/wjwhsjc.do")
	public void wjwhsjc(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		
//		String data = request.getParameter("data");
//		JSONObject json =JSONObject.parseObject(data);
		
		JSONObject json =new JSONObject();
		json.put("sid", "84a1e3cd06a841d0bcfadd7075da1a53");
		json.put("xm", "测试1");
		json.put("zjhm", "112233");
		
		String url = "http://10.83.68.161:80/dataex/api/wjwhsjc";
		JSONObject responseJson = getUserInfo(url,json);
		System.out.println(responseJson);
		response.getWriter().print(responseJson.toString());
	}
	
	
	public static JSONObject getUserInfo(String url,JSONObject json) {
		String newUrl = url+"?appkey=50f4a0686289cd9106cdac7f2f28c61e"+
				  "&signature=64ea409743cbf44deff0000c45bb04a325cd0861"+
				  "&timestamp=20200302";
		System.out.println(newUrl);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(newUrl);
		httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.addHeader("AppKey","583700432526770176");
//		httpPost.addHeader("xm","lifan");
//		httpPost.addHeader("zjhm","112233");
		try {
			StringEntity se = new StringEntity(json.toString(), "UTF-8");
			se.setContentType( "application/json;charset=utf-8");
//			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost);
			JSONObject jsonObject = JSONObject.parseObject(EntityUtils
					.toString(response.getEntity()));
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	public static void main(String[] args) {
//		JSONObject json =new JSONObject();
//		json.put("sid", "84a1e3cd06a841d0bcfadd7075da1a53");
//		json.put("xm", "测试1");
//		json.put("zjhm", "112233");
//		
//		String url = "http://10.83.68.161:80/dataex/api/wjwktjc";
//		JSONObject responseJson = getUserInfo(url,json);
//		System.out.println(responseJson);
//	}
	
	
}
