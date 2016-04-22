package com.wwfly.service.in;

import java.util.List;

import com.wwfly.service.param.TravelRoutineParam;
import com.wwfly.service.param.UserTravelPlanParam;
import com.wwfly.service.param.ViewSpotParam;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;
import com.wwfly.service.result.PhotoSharingData;
import com.wwfly.service.result.TravelInfo;
import com.wwfly.service.result.TravelRoutineData;
import com.wwfly.service.result.UserData;

public interface ITravel {
	public Log addTravelRoutine(TravelRoutineParam travelRoutineParam);
	public Log RegisterATravelRoutine(String userEmail, String routineName);
	public Pair<List<TravelRoutineData>, Log> recommandRoutine(UserTravelPlanParam plan);
	public Log addViewSpotToTravelRoutine(String routineName, ViewSpotParam viewSpot);
	public TravelInfo userRegisteredRoutineInfo(String userEmail);
	public Pair<List<TravelRoutineData>, Log> getViewRoutineFromAViewSpot(String viewSpotName);
	public Pair<List<UserData>, Log> getUsersFromARoutine(String routineName);
	public Pair<Double, Log> getTravelCost(TravelRoutineParam travelRoutineParam);
	public Pair<List<PhotoSharingData>, Log> getPhotosFromAUser(String userEmail);
	public Pair<List<PhotoSharingData>, Log> getPhotosFromATravelRoutine(TravelRoutineParam travelRoutineParam);
	public Pair<List<TravelRoutineData>, Log> getMostRecentTravelRoutine();
	public Log deleteATravelRoutine(String trName, String userEmail);
}
