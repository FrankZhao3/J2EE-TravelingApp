package com.wwfly.cache;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import com.wwfly.db.tables.TravelCost;

public class RedisManager {
	
	public void cacheTable(List<TravelCost> list) {
		Jedis jedis = null;
		try {
			jedis = RedisUtil.getJedis();
			Pipeline pl = jedis.pipelined();
			for(TravelCost tc : list) {
				pl.set(tc.getStartPlace() + tc.getEndPlace(), JsonUtil.toJson(tc));
			}
		} catch (Exception e) {
			e.printStackTrace();
			RedisUtil.returnBrokenJedis(jedis);
		} finally {
			RedisUtil.returnRedis(jedis);
		}
	}
	public TravelCost getTravelCost(String startPlace, String endPlace) {
		Jedis jedis = null;
		TravelCost tc = null;
		try {
			jedis = RedisUtil.getJedis();
			String str = jedis.get(startPlace + endPlace);
			System.out.println(str);
			tc = JsonUtil.toObj(str, TravelCost.class);
		} catch (Exception e) {
			e.printStackTrace();
			RedisUtil.returnBrokenJedis(jedis);
		} finally {
			RedisUtil.returnRedis(jedis);
		}
		return tc;
	}
	private static class InnerClass {
		private static RedisManager manager = new RedisManager();
	}

	public static RedisManager getInstance() {
		return InnerClass.manager;
	}
}
