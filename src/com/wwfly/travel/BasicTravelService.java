package com.wwfly.travel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wwfly.db.service.ClientDataImplement;
import com.wwfly.db.tables.City;
import com.wwfly.db.tables.PhotoSharing;
import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.db.tables.User;
import com.wwfly.db.tables.UserRoutine;
import com.wwfly.db.tables.ViewRoutine;
import com.wwfly.db.tables.ViewSpot;
import com.wwfly.service.in.ITravel;
import com.wwfly.service.param.TravelRoutineParam;
import com.wwfly.service.param.UserTravelPlanParam;
import com.wwfly.service.param.ViewSpotParam;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;
import com.wwfly.service.result.PhotoSharingData;
import com.wwfly.service.result.TravelInfo;
import com.wwfly.service.result.TravelRoutineData;
import com.wwfly.service.result.UserData;

public class BasicTravelService implements ITravel{

	@Override
	public Log addTravelRoutine(TravelRoutineParam travelRoutineParam) {
		TravelRoutine travelRoutine = new TravelRoutine(travelRoutineParam.getName(), travelRoutineParam.getStartDate(), travelRoutineParam.getEndDate(), 
				travelRoutineParam.getCost(), travelRoutineParam.getNumberOfRegistrations(), travelRoutineParam.getMaxRegistration(), travelRoutineParam.getAvailability()
		);
		travelRoutine.setStartingPlace(travelRoutineParam.getStartingPlace());
		travelRoutine.setDestination(travelRoutineParam.getDestination());
		ClientDataImplement clientDataService = new ClientDataImplement();
		return clientDataService.saveATravelRoutine(travelRoutine);
	}

	@Override
	public Log RegisterATravelRoutine(String userEmail, String routineName) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		User user = clientDataService.getUserBasedOnEmail(userEmail);
		TravelRoutine travelRout = clientDataService.getRoutineBasedOnRoutineName(routineName, msg);
		clientDataService.addTravelRoutineToAUser(user, travelRout, msg);
		return msg;
	}

	@Override
	public Pair<List<TravelRoutineData>, Log> recommandRoutine(UserTravelPlanParam plan) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		if(plan.getName() != null) {
			TravelRoutine routine= clientDataService.getRoutineBasedOnRoutineName(plan.getName(), msg);
			List<TravelRoutineData> list = new ArrayList<TravelRoutineData>();
			TravelRoutineData data = copyTravelRoutine(routine);
			list.add(data);
			return new Pair<List<TravelRoutineData>, Log>(list, msg);
		}
		String destination = plan.getDestination();
		String startingPlace = plan.getStartingPlace();
		Double max_cost = plan.getMax_cost();
		Date startDate = plan.getStartDate();
		Date endDate = plan.getEndDate();
		List<TravelRoutine> list_of_routine = clientDataService.getRecommandRoutine(destination, startingPlace, max_cost, startDate, endDate, msg);
		List<TravelRoutineData> list_of_data = new ArrayList<TravelRoutineData>();
		for(TravelRoutine rout : list_of_routine) {
			TravelRoutineData data = copyTravelRoutine(rout);
			list_of_data.add(data);
		}
		return new Pair<List<TravelRoutineData>, Log>(list_of_data, msg);
	}
	
	@Override
	public Log addViewSpotToTravelRoutine(String routineName, ViewSpotParam viewSpot) {
		ClientDataImplement clientDataService = new ClientDataImplement();
		String cityName = viewSpot.getCityName();
		ViewSpot view = new ViewSpot();
		City city = clientDataService.getCityByName(cityName);
		view.setCityId(city.getId());
		view.setReviews(viewSpot.getReviews());
		view.setReviewStars(viewSpot.getReviewStars());
		view.setSpotName(viewSpot.getSpotName());
		return clientDataService.addViewSpotToARoutine(routineName, view);
	}
	
	@Override
	public TravelInfo userRegisteredRoutineInfo(String userEmail) {
		
		if(userEmail == null || userEmail.length() > 25) {
			return null;
		}
		TravelInfo travelInfo = new TravelInfo();
		ClientDataImplement clientDataService = new ClientDataImplement();
		User user = clientDataService.getUserBasedOnEmail(userEmail);
		if(user == null) {
			return null;
		}
		UserData userData = copyUser(user);
		travelInfo.setUser(userData);
		List<TravelRoutineData> list = new ArrayList<TravelRoutineData>();
		for(UserRoutine ur : user.getRoutineList()) {
			list.add(copyTravelRoutine(ur.getRoutine()));
		}
		travelInfo.setList_of_travel(list);
		return travelInfo;
	}
	@Override
	public Pair<List<TravelRoutineData>, Log> getViewRoutineFromAViewSpot(String viewSpotName) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		List<TravelRoutineData> list_data = new ArrayList<TravelRoutineData>();
		Set<ViewRoutine> list_vRout = clientDataService.getTravelRoutineBasedOnViewSpot(viewSpotName, msg);
		if(list_vRout == null) { 
			return new Pair<List<TravelRoutineData>, Log>(null, msg);
		} else {
			for(ViewRoutine vRout : list_vRout) {
				list_data.add(copyTravelRoutine(vRout.getTravelRoutine()));
			}
			return new Pair<List<TravelRoutineData>, Log>(list_data, msg);
		}
	}
	@Override
	public Pair<List<UserData>, Log> getUsersFromARoutine(String routineName) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		List<UserData> list_data = new ArrayList<UserData>();
		Set<UserRoutine> uRout_set = clientDataService.getUserFromRoutine(routineName, msg);
		if(uRout_set == null) { 
			return new Pair<List<UserData>, Log>(null, msg);
		} else {
			for(UserRoutine uRout : uRout_set) {
				list_data.add(copyUser(uRout.getUser()));
			}
			return new Pair<List<UserData>, Log>(list_data, msg);
		}
	}
	
	public Pair<Double, Log> getTravelCost(TravelRoutineParam travelRoutineParam){
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		String startPlace = travelRoutineParam.getStartingPlace();
		String endPlace = travelRoutineParam.getDestination();
		Double cost = clientDataService.getTravelCost(startPlace, endPlace, msg);
		return new Pair<Double, Log>(cost, msg);
	}
	
	@Override
	public Pair<List<PhotoSharingData>, Log> getPhotosFromAUser(String userEmail) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		List<PhotoSharing> listOfPhotos = clientDataService.getPhotosFromUser(userEmail, msg);
		List<PhotoSharingData> photoDataList = new ArrayList<PhotoSharingData>();
		for(PhotoSharing photo : listOfPhotos) {
			photoDataList.add(copyPhoto(photo));
			System.out.println(photo.getPhotoUrl());
		}
		System.out.println(listOfPhotos);
		return new Pair<List<PhotoSharingData>, Log>(photoDataList, msg);
	}

	@Override
	public Pair<List<PhotoSharingData>, Log> getPhotosFromATravelRoutine(
			TravelRoutineParam travelRoutineParam) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		String travelRoutineName = travelRoutineParam.getName();
		List<PhotoSharing> listOfPhotos = clientDataService.getPhotosFromTravelRoutine(travelRoutineName, msg);
		List<PhotoSharingData> photoDataList = new ArrayList<PhotoSharingData>();
		if(listOfPhotos == null || listOfPhotos.size() < 1) {
			msg.appendMsg("No photos yet");
			return new Pair<List<PhotoSharingData>, Log>(photoDataList, msg);
		} else {
			for(PhotoSharing photo : listOfPhotos) {
				photoDataList.add(copyPhoto(photo));
				System.out.println(photo.getPhotoUrl());
			}
			return new Pair<List<PhotoSharingData>, Log>(photoDataList, msg);
		}
	}
	@Override
	public Pair<List<TravelRoutineData>, Log> getMostRecentTravelRoutine() {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		List<TravelRoutine> listOfRecentActivities;
		List<TravelRoutineData> list = new ArrayList<TravelRoutineData>();
		try {
			listOfRecentActivities = clientDataService.getRecentTravelRoutine(msg);
			for(TravelRoutine tr : listOfRecentActivities) {
				list.add(copyTravelRoutine(tr));
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.appendErrorMsg(e.toString());
		}
		return new Pair<List<TravelRoutineData>, Log>(list, msg);
	}
	@Override
	public Log deleteATravelRoutine(String trName, String userEmail) {
		Log msg = new Log();
		ClientDataImplement clientDataService = new ClientDataImplement();
		try {
			msg = clientDataService.deleteATravelRoutineFromAUser(trName, userEmail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.appendErrorMsg(e.toString());
			e.printStackTrace();
		}
		return msg;
	}
	//==========================Helping Method=====================
	private TravelRoutineData copyTravelRoutine(TravelRoutine rout) {
		TravelRoutineData data = new TravelRoutineData(rout.getName(), rout.getStartDate(),
				rout.getEndDate(), rout.getCost(), rout.getNumberOfRegistrations(), rout.getMaxRegistration(), 
				rout.getDestination(), rout.getStartingPlace());
//		String listOfViewSpot = "";
//		for(ViewRoutine spot : rout.getView_list()){
//			listOfViewSpot += spot.getViewSpot().toString() + "\n";
//		}
//		data.setListOfViewSpot(listOfViewSpot);
		return data;
	}
	
	private UserData copyUser(User user) {
		UserData data = new UserData();
		if(user.getAge() != null) {
			data.setAge(user.getAge());
		}
		data.setName(user.getName());
		if(user.getSchool()!=null) {
			data.setSchool(user.getSchool().getName());
		}
		data.setSex(user.getSex());
		data.setUserEmail(user.getUserEmail());
		if(user.getUserPhone()!=null) {
			data.setUserPhone(user.getUserPhone());
		}
		data.setUserId(user.getUserId());
		return data;
	}
	
	private PhotoSharingData copyPhoto(PhotoSharing photo) {
		return new PhotoSharingData(photo.getPhotoUrl(), photo.getRecordTime()
				,photo.getUserReview());
	}
}



