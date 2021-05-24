package com.infinitePossibilities.utils.threads.threadPoolInsertData;

import java.sql.Timestamp;
import com.alibaba.fastjson.JSONObject;
import wfc.service.database.SQL;

public class threadUtil implements Runnable {

	private JSONObject json;
	
	public threadUtil(JSONObject json) {
		this.json = json;
	}

	@Override
	public void run() {
		System.out.println("threadUtil--->"+json);
		System.out.println("当前线程："+Thread.currentThread().getName());
		String stFjId = json.getString("stFjId");
		String stApplyId = json.getString("stApplyId");
		String fjName =json.getString("fjName");
		String url = json.getString("url");
		String time = json.getString("time");
		//String 转 Timestamp
		Timestamp saveTime = Timestamp.valueOf(time.toString());

		String sql = "insert into DANGAN_FJ(ST_FJ_ID,ST_APPLY_ID,FJ_NAME,URL,TIME) values (?,?,?,?,?)";
		Object[] objects = new Object[] { stFjId, stApplyId, fjName,url,saveTime };
		SQL.execute(sql,objects);
		System.out.println("\n");
	}
}
