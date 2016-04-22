package com.wwfly.cache;

import java.util.List;

import com.wwfly.db.service.ClientDataImplement;
import com.wwfly.db.tables.TravelCost;

public class InitConfig {
	public InitConfig() {
		super();
		this.readInitData();
		
	}
	private void readInitData() {
		try {
			ClientDataImplement clientDataService = new ClientDataImplement();
			List<TravelCost> list = clientDataService.initRedis();
			RedisManager.getInstance().cacheTable(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
