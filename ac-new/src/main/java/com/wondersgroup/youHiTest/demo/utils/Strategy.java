package com.wondersgroup.youHiTest.demo.utils;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface Strategy {

	String requestData(JSONObject json);
}
