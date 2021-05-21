package com.infinitePossibilities.youHiTest.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.infinitePossibilities.youHiTest.demo.utils.SendService;
import com.infinitePossibilities.youHiTest.demo.utils.Strategy;

@SendService(sheng="上海市")
public class shanghai implements Strategy{

	@Override
	public String requestData(JSONObject json) {
		json.put("归属地", "上海市");
		return json.toString();
	}

}
