package com.wondersgroup.youHiTest.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.youHiTest.demo.utils.SendService;
import com.wondersgroup.youHiTest.demo.utils.Strategy;

@SendService(sheng="江苏省")
public class jiangsu implements Strategy{

	@Override
	public String requestData(JSONObject json) {
		json.put("归属地", "江苏省");
		return json.toString();
	}

}
