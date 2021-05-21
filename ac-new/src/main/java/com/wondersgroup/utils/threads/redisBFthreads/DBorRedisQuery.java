package com.wondersgroup.utils.threads.redisBFthreads;


import com.wondersgroup.redis.RedisUtil;
import tw.tool.helper.LogHelper;
import wfc.service.database.RecordSet;
import wfc.service.database.SQL;


public class DBorRedisQuery {
	
	public String dbOrRedisQuery(String key) {
		synchronized(key){
			String applynoId="";
			applynoId = 	RedisUtil.get(key);
		 	if(applynoId==null){
		 		LogHelper.info("当前线程 false："+Thread.currentThread().getName());
		 		String sql = "select * from DANGAN_FJ where ST_FJ_ID=?";
				Object[] objects = new Object[] {key};
				RecordSet rs = SQL.execute(sql,objects);
				while (rs.next()) {
					applynoId = rs.getString("ST_APPLY_ID");
				}
				System.out.println(applynoId);
				RedisUtil.set(key,applynoId,Long.parseLong("60"));//60秒有效期
				System.out.println("从数据库中获取......"+applynoId);
				return applynoId;
		 	}
		 	LogHelper.info("当前线程 true："+Thread.currentThread().getName());
			System.out.println("从redis中获取......"+applynoId);
			return applynoId;
		}
	}
}
