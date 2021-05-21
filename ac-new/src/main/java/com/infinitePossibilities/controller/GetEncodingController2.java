package com.infinitePossibilities.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLDecoder;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import tw.ecosystem.reindeer.web.HttpReqRes;
import wfc.service.log.Log;

@	Controller
public class GetEncodingController2 {

	@RequestMapping("/yb/getString2.do")
	public void getString(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		HttpReqRes httpReqRes = new HttpReqRes(request, response);
		
		
		String da = httpReqRes.getParameter("data");
		Log.info("data222222222222222  :  "+da);
		
		String str = URLDecoder.decode(da,"UTF-8");
		Log.info("data333333333333  :  "+str);
		
		String data2 = new String (str.getBytes("UTF-8"));
		
		
//		Log.info("getEncoding--ISO-UTF-8：" + new String(str.getBytes("ISO-8859-1"), "UTF-8"));
//		Log.info("--GB2312 ：" + new String(str.getBytes(), "GB2312"));
//		Log.info("--ISO-8859-1 ：" + new String(str.getBytes(), "ISO-8859-1"));
//		Log.info("--UTF-8 ：" + new String(str.getBytes(), "UTF-8"));
//		Log.info("--GBK ：" + new String(str.getBytes(), "GBK"));
//
//		Log.info("--GB2312 ---2：" + new String(str.getBytes("GB2312")));
//		Log.info("--ISO-8859-1 ---2：" + new String(str.getBytes("ISO-8859-1")));
//		Log.info("--UTF-8--2：" + new String(str.getBytes("UTF-8")));
//		Log.info("--GBK--2 ：" + new String(str.getBytes("GBK")));
//
//		Log.info("UTF-8--GB2312 ：" + new String(str.getBytes("UTF-8"), "GB2312"));
//		Log.info("UTF-8--ISO-8859-1 ：" + new String(str.getBytes("UTF-8"), "ISO-8859-1"));
//		Log.info("UTF-8--UTF-8 ：" + new String(str.getBytes("UTF-8"), "UTF-8"));
//		Log.info("UTF-8--GBK ：" + new String(str.getBytes("UTF-8"), "GBK"));
//
//		Log.info("GB2312--UTF-8 ：" + new String(str.getBytes("GB2312"), "UTF-8"));
//		Log.info("GB2312--ISO-8859-1 ：" + new String(str.getBytes("GB2312"), "ISO-8859-1"));
//		Log.info("GB2312--GBK ：" + new String(str.getBytes("GB2312"), "GBK"));
//		Log.info("GB2312--GB2312 ：" + new String(str.getBytes("GB2312"), "GB2312"));
//
//		Log.info("ISO-8859-1--GBK ：" + new String(str.getBytes("ISO-8859-1"), "GBK"));
//		Log.info("ISO-8859-1--UTF-8 ：" + new String(str.getBytes("ISO-8859-1"), "UTF-8"));
//		Log.info("ISO-8859-1--GB2312 ：" + new String(str.getBytes("ISO-8859-1"), "GB2312"));
//		Log.info("ISO-8859-1--ISO-8859-1 ：" + new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));
//
//		Log.info("GBK--GB2312：" + new String(str.getBytes("GBK"), "GB2312"));
//		Log.info("GBK--ISO-8859-1：" + new String(str.getBytes("GBK"), "ISO-8859-1"));
//		Log.info("GBK--UTF-8：" + new String(str.getBytes("GBK"), "UTF-8"));
//		Log.info("GBK--GBK：" + new String(str.getBytes("GBK"), "GBK"));
//
//		String fhStr1 =new String(str.getBytes("ISO-8859-1"), "UTF-8");
//		String fhStr2 = new String(str.getBytes("UTF-8"));
		
		JSONObject json = new JSONObject();
		json.put("data","====="+str+"+++++++"+data2);
		Log.info("JSON=======：" +json);
		
		httpReqRes.writeJson(json.toString()); 
		
	}
	
	@RequestMapping("/yb/getByte2.do")
	public void getByte(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		
//		ServletInputStream ris = request.getInputStream();
//		StringBuilder content = new StringBuilder();
//		byte[] b = new byte[1024];
//		int lens = -1;
//		while ((lens = ris.read(b)) > 0) {
//			content.append(new String(b, 0, lens));
//		}
//		String str = content.toString();// 内容

		ServletInputStream sis = request.getInputStream();
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = sis.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
		String str = new String(in2b);
		

		Log.info("data222222222222222  :  "+str);
		String data2 = new String (str.getBytes("UTF-8"));
		Log.info("data333333333333  :  "+data2);
		
		
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
		
		String fhStr1 =new String(str.getBytes("ISO-8859-1"), "UTF-8");
		String fhStr2 = new String(str.getBytes("UTF-8"));
		
		JSONObject json = new JSONObject();
		json.put("data", fhStr1+"----"+fhStr2 +"====="+data2);
		Log.info("JSON=======：" +json);
		
//		InputStream is = new ByteArrayInputStream((json.toString()).getBytes("UTF-8"));
//		return is;
		
		response.getWriter().print(json.toString());
		
		
	}
	
	
	
	
	
}
