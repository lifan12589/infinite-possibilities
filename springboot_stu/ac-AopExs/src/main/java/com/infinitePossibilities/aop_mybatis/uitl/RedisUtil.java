package com.infinitePossibilities.aop_mybatis.uitl;

import redis.clients.jedis.Jedis;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RedisUtil {
//	redis-server.exe redis.windows.conf   --->启动 redis
//	redis-cli.exe -h 127.0.0.1 -p 6380    ---->操作 redis

	public RedisUtil() {

	}
	private static Jedis jedis;// redis实例
	private static String host;//地址
	private static String port;//端口
	private static String password;//授权密码
	private static String timeout;//超时时间：单位MS
	private static String maxIdle;//最大空闲数：空闲链接数大于maxIdle时,将进行回收
	private static String maxActive;//最大连接数：能够同时建立的"最大链接个数"
	private static String maxWait;//最大等待时间：单位ms
	private static String testOnBorrow;//在获取连接时，是否验证有效性

	static{
		//加载properties配置文件
		Properties properties = new Properties();
		InputStream is = RedisUtil.class.getClassLoader().getResourceAsStream("redis_config.properties");
		try {
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		host = properties.getProperty("redis.single.host");
		port = properties.getProperty("redis.single.port");
		password = properties.getProperty("redis.password");
		timeout = properties.getProperty("redis.timeout");
		maxIdle = properties.getProperty("redis.maxIdle");
		maxActive = properties.getProperty("redis.maxActive");
		maxWait = properties.getProperty("redis.maxWait");
		testOnBorrow = properties.getProperty("redis.testOnBorrow");
		// 得到Jedis/JedisPool实例并且设置配置
		jedis = new Jedis(host,Integer.parseInt(port),Integer.parseInt(timeout));

	}

	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set (final String key,String  value){
		boolean result = false;
		try {
			jedis.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("set cache error");
		}
		return result ;
	}


	public static boolean setnx (final String key,String  value){
		boolean result = false;
		try {
			jedis.setnx(key,value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("set cache error");
		}
		return result ;
	}




	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	public static String get(final String key) {
		String result =null;
		result = jedis.get(key);
		return result;
	}


	/**
	 * 删除key对应的value
	 * @param key
	 */
	public static void del(final String key) {
		if(key!=null&&key.length()>=1&&!key.equals("")&&jedis.exists(key)){
			jedis.del(key);
		}
	}

	/**
	 * 判断缓存中是否有key对应的value
	 * @param key
	 * @return
	 */
	public static boolean exists(final String key) {
		return jedis.exists(key);
	}

	/**
	 * 写入缓存（规定缓存时间）
	 * @param key
	 * @param value
	 * @param expireSecond
	 * @return
	 */
	public static boolean set(final String key,String value,Long expireSecond) {
		boolean result = false;
		try {
			// NX代表不存在才set,EX代表秒,NX代表毫秒
			jedis.set(key, value, "NX", "EX", expireSecond);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("set cache error on time");
		}
		return result;
	}

	/**
	 * <p>
	 * 通过key向list头部添加字符串
	 * </p>
	 *
	 * @param key
	 * @param strs
	 *            可以使一个string 也可以使string数组
	 * @return 返回list的value个数
	 */
	public static Long lpush(String key, String... strs) {
		Long res = null;
		res = jedis.lpush(key, strs);
		return res;
	}


	public static void main(String[] args) {

		del("上海市");
	}

}
