package com.wwfly.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.db.tables.School;
import com.wwfly.db.tables.User;
import com.wwfly.service.in.ILogin;
import com.wwfly.service.param.LoginParam;

public class TestLogin {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8080";
		String loginUrl = "/client/login";
		String registerUrl = "/client/register";
		HessianProxyFactory shpf = new HessianProxyFactory();
		ILogin basicLogSer = (ILogin) shpf.create(ILogin.class, url + loginUrl);
//		IRegister basicRegSer = (IRegister) shpf.create(IRegister.class, url + registerUrl);
//		User user1 = new User("Peter", 21, "m", "peter@gmail.com", "408-493-3829");
//		User user2 = new User("MARS", 71, "m", "peter@gmail.com", "408-493-3169");
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
//		System.out.println(basicRegSer.register(user1, username, password));
//		System.out.println(basicRegSer.register(user2, username1, password));
//		System.out.println(basicRegSer.register(user3, username2, password));
		LoginParam param = new LoginParam(username, password);
		System.out.println(basicLogSer.login(param).toString());
	}
}
