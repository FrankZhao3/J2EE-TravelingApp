package com.wwfly.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.db.tables.Login;
import com.wwfly.db.tables.School;
import com.wwfly.db.tables.User;
import com.wwfly.service.in.IRegister;
import com.wwfly.service.param.LoginParam;
import com.wwfly.service.param.RegisterParam;

public class TestRegister {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://127.0.0.1:8080/WWFLY";
		String loginUrl = "/client/login";
		String registerUrl = "/client/register";
		HessianProxyFactory shpf = new HessianProxyFactory();
		IRegister basicRegSer = (IRegister) shpf.create(IRegister.class, url + registerUrl);
//		RegisterParam user1 = new RegisterParam("Chris", 20, "M", "chris@gmail.com", "478-433-3819", "berkeley");
		RegisterParam user2 = new RegisterParam("MARS", 71, "m", "peter@gmail.com", "408-493-3169");
//		User user3 = new User("Mary", 21, "f", "mary@gmail.com", "408-423-3129");
//		School school1 = new School("BSD", 2000);
//		School school2 = new School("berkeley", 20000);
//		user1.setSchool(school1);
//		user1.setRoutineList(null);
//		user2.setSchool(school1);
//		user2.setRoutineList(null);
//		user3.setSchool(school2);
//		user3.setRoutineList(null);
		String username = "zhaohp2012@hotmail.com";
//		String username1 = "peter123";
//		String username2 = "frank123";
		String password = "123456";
		LoginParam login = new LoginParam(username, password);
//		System.out.println(basicRegSer.register(user2));
		System.out.println(basicRegSer.addAccount(login));
//		System.out.println(basicRegSer.register(user2, username1, password));
//		System.out.println(basicRegSer.register(user3, username2, password));
	}
}