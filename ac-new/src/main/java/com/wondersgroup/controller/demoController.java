package com.wondersgroup.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

@Controller
public class demoController {
//	public static void main(String[] args) {
//		String roadId ="";
//		String roadName = "共和新路";
//		JSONObject requestData = new JSONObject();
//		requestData.put("type", "1");
//		requestData.put("serviceid", "fcedf9a2116549c395f44b1ba70529bd");
//		String url = "http://10.81.65.225:8000/lzj/item/getRoadInfo";
//		JSONArray responseData = HttpClientUtil.getData(url, requestData);
//		System.out.println("返回数据:"+"\n"+responseData);
//		JSONObject jsons = new JSONObject();
//		if(roadName.equals("")){
//			return;
//		}else{
//			for(int i =0; i<responseData.size();i++){
//				jsons = responseData.getJSONObject(i);
//				if(roadName.equals(jsons.getString("name"))){
//				 roadId = jsons.getString("rid");
//				}
//			}
//			System.out.println(roadId);
//		}
//
//	}

	@RequestMapping("/wjcsdlxk.do")
	public void  wjcsdlxk (HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException{
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/piain;charset=UTF-8");
	String roadId ="";
	String roadName = request.getParameter("roadName");
	JSONObject requestData = new JSONObject();
	requestData.put("type", "1");
	requestData.put("serviceid", "fcedf9a2116549c395f44b1ba70529bd");
	String url = "http://10.81.65.225:8000/lzj/item/getRoadInfo";
	JSONArray responseData = HttpClientUtil.getData(url, requestData);
	System.out.println("返回数据:"+"\n"+responseData);
	JSONObject jsons = new JSONObject();
		if(roadName.equals("")){
		return;
	}else{
		for(int i =0; i<responseData.size();i++){
			jsons = responseData.getJSONObject(i);
			if(roadName.equals(jsons.getString("name"))){
			 roadId = jsons.getString("rid");
			}
		}
	}
	response.getWriter().write(roadId);
}
	
	
}
