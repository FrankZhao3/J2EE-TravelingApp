package com.wwfly.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wwfly.db.service.ClientDataImplement;
import com.wwfly.db.tables.School;
import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.db.tables.User;
import com.wwfly.service.in.IInfo;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;
import com.wwfly.service.result.TravelRoutineData;
import com.wwfly.service.result.UserData;

public class BasicUserInfoService implements IInfo {

	@Override
	public Pair<UserData, Log> getInfoBasedOnId(Integer id) {
		Log msg = new Log();
		ClientDataImplement clientFactory = new ClientDataImplement();
		User user = clientFactory.getUserBasedOnId(id, msg);
		UserData userData = new UserData(user.getName(), user.getAge(), user.getSex(), 
										user.getUserEmail(), user.getUserPhone());
		if(user.getSchool()!= null) {
			userData.setSchool(user.getSchool().getName());
		}
		return new Pair<UserData, Log>(userData, msg);
	}
	
	@Override
	public Pair<List<UserData>, Log> getInfoBaseOnName(String name) {
		Log msg = new Log();
		ClientDataImplement clientFactory = new ClientDataImplement();
		List<User> userlist = clientFactory.getUsersBasedOnName(name, msg);
		List<UserData> dataList = new ArrayList<UserData>();
		for(User user : userlist) {
			UserData userData = new UserData(user.getName(), user.getAge(), user.getSex(), 
					user.getUserEmail(), user.getUserPhone());
			if(user.getSchool()!= null) {
				userData.setSchool(user.getSchool().getName());
			}
			dataList.add(userData);
		}
		return new Pair<List<UserData>, Log>(dataList, msg);
	}
	public Pair<List<UserData>, Log> getInfoBaseOnEmail(String emailName) {
		Log msg = new Log();
		List<UserData> dataList = new ArrayList<UserData>();
		if(emailName == null) {
			 return new Pair<List<UserData>, Log>(dataList, msg);
		}
		ClientDataImplement clientFactory = new ClientDataImplement();
		List<User> userlist = clientFactory.getUsersBasedOnEmail(emailName, msg);
		for(User user : userlist) {
			UserData userData = new UserData(user.getName(), user.getAge(), user.getSex(), 
					user.getUserEmail(), user.getUserPhone());
			if(user.getSchool()!= null) {
				userData.setSchool(user.getSchool().getName());
			}
			dataList.add(userData);
		}
		return new Pair<List<UserData>, Log>(dataList, msg);
	}
	@Override
	public Log deleteUserById(Integer id) {
		ClientDataImplement clientFactory = new ClientDataImplement();
		return clientFactory.deleteUserBasedOnId(id);
	}

	@Override
	public Log deleteUserByName(String name) {
		ClientDataImplement clientFactory = new ClientDataImplement();
		return clientFactory.deleteUserBasedOnName(name);
	}
	
	@Override
	public Log editUser(UserData userData) {
		ClientDataImplement clientFactory = new ClientDataImplement();
		User user = new User(userData.getName(), userData.getAge(), userData.getSex(),
				userData.getUserEmail(), userData.getUserPhone());
		if(userData.getSchool()!=null) {
			School school = clientFactory.getSchoolBasedOnName(userData.getSchool());
			user.setSchool(school);
		}
		User user_get_from_email = clientFactory.getUserBasedOnEmail(userData.getUserEmail());
		if(user_get_from_email.getUserId() == null) {
			Log msg = new Log();
			msg.setErrorMsg("No such user");
			return msg;
		} else {
			user.setUserId(user_get_from_email.getUserId());
			user.setUserSchoolId(user_get_from_email.getUserSchoolId());
			return clientFactory.updateUserInfo(user);
		}
	}

	@Override
	public Pair<Set<User>, Log> getStudentInfoBasedOnSchoolId(Integer id) {
		return null;
	}
	
	@Override
	public Pair<List<TravelRoutineData>, Log> getTravelRoutineBasedOnEmailName(String emailName) {
		Log msg = new Log();
		ClientDataImplement clientFactory = new ClientDataImplement();
		List<TravelRoutineData> list = new ArrayList<TravelRoutineData>();
		try {
			try {
				List<TravelRoutine> trList = clientFactory.getTRListBasedOnEmailName(emailName);
				for(TravelRoutine tr : trList) {
					list.add(copyTravelRoutine(tr));
				}
			} catch (Exception e) {
				e.printStackTrace();
				msg.appendErrorMsg(e.toString());
			}
			return new Pair<List<TravelRoutineData>, Log>(list, msg);
		} catch(Exception e) {
			msg.setErrorMsg(e.toString());
			e.printStackTrace();
		}
		return null;
	}
	//==========================Helping Method=====================
	private TravelRoutineData copyTravelRoutine(TravelRoutine rout) {
		TravelRoutineData data = new TravelRoutineData(rout.getName(), rout.getStartDate(),
				rout.getEndDate(), rout.getCost(), rout.getNumberOfRegistrations(), rout.getMaxRegistration(), 
				rout.getDestination(), rout.getStartingPlace());
		return data;
	}
}
