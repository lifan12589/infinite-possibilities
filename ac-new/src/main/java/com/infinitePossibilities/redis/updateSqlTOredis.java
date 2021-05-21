package com.infinitePossibilities.redis;

import wfc.service.database.RecordSet;
import wfc.service.database.SQL;

public class updateSqlTOredis {

	public static void main(String[] args) {
		int count = 2;
		String st_fj_id = "0000001";
		String insertSql = "update dangan_fj set count = count-? where st_fj_id = ? and count>=2";
		Object[] insertObject = new Object[] {count,st_fj_id};
		RecordSet rs  = SQL.execute(insertSql,insertObject);
		 int number = rs.TOTAL_RECORD_COUNT;
		//影响行数
		System.out.println("数据库影响行数"+number);
		if(number>=1){

//			更改redis缓存数据
			String serchRedis = (String) RedisUtil.get("count");
			int redisNu = Integer.parseInt(serchRedis);
			redisNu = redisNu-2;
			boolean setRedis = RedisUtil.set("count", redisNu+"");
			System.out.println( setRedis+"\n"+serchRedis+"\n"+redisNu);
		}
	
	
	}
}
