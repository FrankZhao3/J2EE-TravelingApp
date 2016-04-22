package com.wwfly.db.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wwfly.db.tables.City;
import com.wwfly.db.tables.PhotoSharing;
import com.wwfly.db.tables.School;
import com.wwfly.db.tables.TravelCost;
import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.db.tables.User;
import com.wwfly.db.tables.UserRoutine;
import com.wwfly.db.tables.ViewRoutine;
import com.wwfly.db.tables.ViewSpot;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;

public interface IClientData {
	//USER
	public Pair<Log, Boolean> Userlogin(String username, String password); 
	public Log UserRegister(User user);
	public Log AcctRegister(String username, String password);
	public School getSchoolBasedOnName(String name);
	public User getUserBasedOnId(Integer id, Log msg);
	public List<User> getUsersBasedOnName(String name, Log msg);
	public List<User> getUsersBasedOnEmail(String email, Log msg);
	public Log deleteUserBasedOnId(Integer id);
	public Log deleteUserBasedOnName(String name);
	public Log updateUserInfo(User user);
	public Set<User> getUserListBasedOnSchoolId(Integer school_id, Log msg);
	public User getUserBasedOnEmail(String userEmail) ;
	public List<PhotoSharing> getPhotosFromUser(String userEmail, Log msg); 
	List<TravelRoutine> getTRListBasedOnEmailName(String emailName) throws Exception;
	//TRAVEL
	public Log saveATravelRoutine(TravelRoutine travelRoutine);
	public TravelRoutine getRoutineBasedOnRoutineName(String routineName, Log msg);
	public List<TravelRoutine> getRecommandRoutine(String destination, String startingPlace, Double max_cost, Date startDate, Date endDate, Log msg);
	public Log addViewSpotToARoutine(String routineName, ViewSpot viewSpot);
	public City getCityByName(String cityName);
	public ViewSpot getViewSpotByName(String name);
	public Set<ViewRoutine> getTravelRoutineBasedOnViewSpot(String viewSpotName, Log msg);
	public Set<UserRoutine> getUserFromRoutine(String routineName, Log msg);
	public List<PhotoSharing> getPhotosFromTravelRoutine(String name, Log msg);
	public List<TravelRoutine> getRecentTravelRoutine(Log msg) throws Exception;
	public Log deleteATravelRoutineFromAUser(String trName, String userEmail) throws Exception;
	//Cache
	public List<TravelCost> initRedis();
	public Double getTravelCost(String startPlace, String endPlace, Log msg);
}
