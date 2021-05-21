package com.wondersgroup.youHiTest.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.wondersgroup.youHiTest.demo.utils.SendService;
import com.wondersgroup.youHiTest.demo.utils.Strategy;

@SendService(sheng="浙江省")
public class zhejiang implements Strategy{

	@Override
	public String requestData(JSONObject json) {
		json.put("归属地", "浙江省");
		return json.toString();
	}

}
