package com.tianliangedu.job001.utils;

import redis.clients.jedis.Jedis;

public class RedisOperUtil {
	private Jedis jedis;

	public static RedisOperUtil getInstance() {
		return new RedisOperUtil(SystemConfigParas.redis_ip,
				SystemConfigParas.redis_passport, SystemConfigParas.redis_auth);
	}

	public Jedis getJedis() {
		return jedis;
	}

	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}

	public RedisOperUtil(String ip, int port, String password) {
		// 连接本地的 Redis 服务
		jedis = new Jedis(ip, port);
		jedis.auth(password);
	}

	public String get(String key) {
		String val = jedis.get(key);
		if (val == null) {
			val = "跑到太空去了!";
		}
		return val;
	}

	public void set(String key, String value) {
		jedis.set(key, value);
	}

	public static void main(String[] args) {
		// String ip = "192.168.1.33";
		// int port = 6379;
		// String password = "tianliangedu";
		String ip = SystemConfigParas.redis_ip;
		int port = SystemConfigParas.redis_passport;
		String password = SystemConfigParas.redis_auth;
		RedisOperUtil redisOperUtil = new RedisOperUtil(ip, port, password);

		redisOperUtil.getJedis().flushAll();

		// redisOperUtil.set("k1_from_eclipse", "v1_from_eclipse");
		// System.out.println(redisOperUtil.get("k1_from_eclipse"));

		System.out.println("done!");
	}
}
