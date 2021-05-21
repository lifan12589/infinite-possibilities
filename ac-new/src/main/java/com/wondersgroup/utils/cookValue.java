package com.wondersgroup.utils;


import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.utils.requestUtils.HttpApiClient;

@Controller
public class cookValue {
	
	
	@RequestMapping("/cookValue.do")
	public void dangAnIndex(
			@CookieValue("JSESSIONID") String sessionId,
			@CookieValue("wondersLog_zwdt_sdk") String wondersLog_zwdt_sdk,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		wondersLog_zwdt_sdk = URLDecoder.decode(wondersLog_zwdt_sdk,"UTF-8");
		System.out.println(sessionId);
		System.out.println(wondersLog_zwdt_sdk);
		response.getWriter().print(sessionId+"\n"+wondersLog_zwdt_sdk);
	}
	
	public static void main(String[] args) {
		String accessToken = HttpApiClient.getAccessToken("", "");
		System.out.println(accessToken);
	}
	
	
	
	
	
	
	
	
	
	
}
