package com.infinitePossibilities.redis.jedisConfing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:redis_config.properties")
public class RedisConfig {

	@Value("${redis.single.host}")
	private String host;

	@Value("${redis.single.port}")
	private int port;

	// 配置Redis连接池
	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		// 最大空闲数
		poolConfig.setMaxIdle(30);
		// 最大连接数
		poolConfig.setMaxTotal(50);
		// 最大等待毫秒数
		poolConfig.setMaxWaitMillis(2000);
		return poolConfig;
	}

	@Bean
	public JedisPool jedisPool() {
		JedisPool jedisPool = new JedisPool(jedisPoolConfig(), host, port);
		return jedisPool;
	}
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("127.0.0.1",6379);
		System.out.println(jedis.ping());
		
	}

	// 集群版
	/*
	 * @Bean public JedisCluster jedisCluster(){ Set<HostAndPort> set = new
	 * HashSet<HostAndPort>(); HostAndPort hostAndPort = new
	 * HostAndPort("127.0.0.1", 6379); set.add(hostAndPort); JedisCluster
	 * jedisCluster = new JedisCluster(set, jedisPoolConfig()); return
	 * jedisCluster; }
	 */

}
