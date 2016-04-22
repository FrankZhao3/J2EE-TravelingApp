package com.wwfly.test;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.service.param.TravelRoutineParam;
import com.wwfly.service.param.UserTravelPlanParam;
import com.wwfly.service.param.ViewSpotParam;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;
import com.wwfly.service.result.PhotoSharingData;
import com.wwfly.service.result.TravelRoutineData;
import com.wwfly.service.result.UserData;
import com.wwfly.service.in.ITravel;
public class TestTravel {

	public static void main(String[] args) throws MalformedURLException {
		String url = "http://localhost:8080/";
		String TravelUrl = "/client/travel";
		HessianProxyFactory shpf = new HessianProxyFactory();
		ITravel basicTravelSer = (ITravel) shpf.create(ITravel.class, url + TravelUrl);
		//add a new travel routine
//		Date date = new Date();
//		Date date2 = new Date();
//		TravelRoutineParam trParam = new TravelRoutineParam("哈尔滨之旅", date, date2, 10000.0, 0, 100, "Y", "哈尔滨", "深圳");
//		System.out.println(basicTravelSer.addTravelRoutine(trParam));
		
		
		//test RegisterATravelRoutine
		//		System.out.println(basicTravelSer.RegisterATravelRoutine("chris@gmail.com", "Taiwan"));
		
		
		//test recommandRoutine
//		UserTravelPlanParam plan = new UserTravelPlanParam();
//		plan.setDestination("California");		
//		Pair<List<TravelRoutineData>, Log> pair = basicTravelSer.recommandRoutine(plan);
//		System.out.println(pair.second);
//		for(TravelRoutineData rout: pair.first) {
//			System.out.println(rout);
//		}
		//test addViewSpotToTravelRoutine
//		ViewSpotParam viewSpot = new ViewSpotParam("长白山", 4, "very good", "长春市");
//		System.out.println(basicTravelSer.addViewSpotToTravelRoutine("哈尔滨之旅", viewSpot));
		
		//test TravelInfo
//		System.out.println(basicTravelSer.userRegisteredRoutineInfo("chris@gmail.com"));
		
		//test: print out all routine that goes through a ViewSpot
//		Pair<List<TravelRoutineData>, Log> pair = basicTravelSer.getViewRoutineFromAViewSpot("桂林");
//		System.out.println(pair.second);
//		System.out.println(pair.first);
		
		//test: cache
//		Pair<List<UserData>, Log> pair = basicTravelSer.getUsersFromARoutine("Hawaii Trip");
//		System.out.println(pair.second);
//		System.out.println(pair.first);
// 		test getPhotosFromAUser	
//		Pair<List<PhotoSharingData>, Log> pair  = basicTravelSer.getPhotosFromAUser("chloe@gmail.com");
//		System.out.println(pair.second);
//		System.out.println(pair.first);
//		TravelRoutineParam trp = new TravelRoutineParam();
//		trp.setName("dsjajkdjsal");
//		Pair<List<PhotoSharingData>, Log> pair  = basicTravelSer.getPhotosFromATravelRoutine(trp);
//		System.out.println(pair.second);
//		System.out.println(pair.first);
		
		//test: recent TravelRoutine
		//System.out.println(basicTravelSer.getMostRecentTravelRoutine());
		System.out.println(basicTravelSer.deleteATravelRoutine("Hawaii Trip", "zhaohp2012@hotmail.com"));
	}
}
