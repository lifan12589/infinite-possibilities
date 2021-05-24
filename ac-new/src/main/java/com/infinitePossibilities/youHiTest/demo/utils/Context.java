package com.infinitePossibilities.youHiTest.demo.utils;

import com.alibaba.fastjson.JSONObject;

public class Context {

	public String calRecharge(JSONObject json) throws Exception {
		StrategyFactory strategyFactory =  StrategyFactory.getstrategyFactory();
		String sheng = json.containsKey("sheng")?json.getString("sheng"):"";
		Strategy strategy = strategyFactory.getClass(sheng);
		return strategy.requestData(json);
		
	}
	
}
