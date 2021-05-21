package com.wondersgroup.youHiTest.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.youHiTest.demo.utils.Context;
import com.wondersgroup.youHiTest.demo.utils.requestToJson;
import tw.tool.helper.LogHelper;


@Controller
public class ChongGouController {

	@RequestMapping("/sendAndSave.do")
	public void dangAnSendAndSave(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");
		String jsonString = requestToJson.getRequestJsonString(request);
		System.out.println(jsonString);
		JSONObject json =  JSONObject.parseObject(jsonString);
		System.out.println("json--->"+json);
		Context context = new Context();
		String fhJson = context.calRecharge(json);
		LogHelper.info("逻辑执行完毕！");
		System.out.println(fhJson);
		response.getWriter().print(fhJson.toString());
	}
}
