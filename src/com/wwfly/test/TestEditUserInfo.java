package com.wwfly.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.service.in.IInfo;
import com.wwfly.service.result.UserData;
public class TestEditUserInfo {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://127.0.0.1:8080/WWFLY";
		String InfoUrl = "/client/info";
		HessianProxyFactory shpf = new HessianProxyFactory();
		IInfo basicInfoSer = (IInfo) shpf.create(IInfo.class, url + InfoUrl);
		//Info test
//		System.out.println("getInfoBasedOnId: ");
//		Pair<UserData, Log> pair= basicInfoSer.getInfoBasedOnId(15);
//		System.out.println(pair.second.toString());
//		System.out.println(pair.first.toString());
		
//		System.out.println("getInfoBaseOnName: ");
//		Pair<List<UserData>, Log> pair = basicInfoSer.getInfoBaseOnName("Peter");
//		System.out.println(pair.second.toString());
//		for(UserData userData : pair.first) {
//			System.out.println(userData.toString());
//		}
		
//		System.out.println("getStudentInfoBasedOnSchoolId: ");
//		System.out.println(basicInfoSer.getStudentInfoBasedOnSchoolId(12));
		
		//deletion test
//		System.out.println(basicInfoSer.deleteUserById(15));
//		System.out.println(basicInfoSer.deleteUserByName("Peter"));
		
		//update test
//		UserData userData = new UserData("MARS", 18, "m", "mars@gmail.com", "408-493-1111");
//		System.out.println(basicInfoSer.editUser(userData));
	}
}
