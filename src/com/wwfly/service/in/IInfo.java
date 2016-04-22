package com.wwfly.service.in;

import java.util.List;
import java.util.Set;

import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.db.tables.User;
import com.wwfly.service.result.Log;
import com.wwfly.service.result.Pair;
import com.wwfly.service.result.TravelRoutineData;
import com.wwfly.service.result.UserData;

public interface IInfo {
	public Pair<UserData, Log> getInfoBasedOnId(Integer id);
	public Pair<List<UserData>, Log> getInfoBaseOnName(String name);
	public Pair<List<UserData>, Log> getInfoBaseOnEmail(String emailName);
	public Log deleteUserById(Integer id);
	public Log deleteUserByName(String name);
	public Log editUser(UserData user);
	public Pair<Set<User>, Log> getStudentInfoBasedOnSchoolId(Integer id);
	public Pair<List<TravelRoutineData>, Log> getTravelRoutineBasedOnEmailName(String emailName);
}
