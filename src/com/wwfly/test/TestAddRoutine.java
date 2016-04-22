package com.wwfly.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.db.tables.User;
import com.wwfly.service.in.IInfo;

public class TestAddRoutine {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://127.0.0.1:8080/WWFLY";
		String InfoUrl = "/client/info";
		HessianProxyFactory shpf = new HessianProxyFactory();
		IInfo basicInfoSer = (IInfo) shpf.create(IInfo.class, url + InfoUrl);
		//User user = basicInfoSer.getUserBasedOnId(14);
		//user.getName();
	}
}
