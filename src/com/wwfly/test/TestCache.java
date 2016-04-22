package com.wwfly.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.cache.InitConfig;
import com.wwfly.service.in.ITravel;
import com.wwfly.service.param.TravelRoutineParam;

public class TestCache {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://127.0.0.1:8080/WWFLY";
		String TravelUrl = "/client/travel";
		HessianProxyFactory shpf = new HessianProxyFactory();
		ITravel basicTravelSer = (ITravel) shpf.create(ITravel.class, url + TravelUrl);
		new InitConfig();
		TravelRoutineParam travelRoutineParam = new TravelRoutineParam();
		travelRoutineParam.setDestination("cupertino");
		travelRoutineParam.setStartingPlace("Berkeley");
		System.out.println(basicTravelSer.getTravelCost(travelRoutineParam));
	}
}
