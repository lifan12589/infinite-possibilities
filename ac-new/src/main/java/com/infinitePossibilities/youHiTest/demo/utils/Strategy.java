package com.infinitePossibilities.youHiTest.demo.utils;

import com.alibaba.fastjson.JSONObject;

public interface Strategy {

	String requestData(JSONObject json);
}
