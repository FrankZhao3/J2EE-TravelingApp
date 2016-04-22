package com.wwfly.service.result;

import java.io.Serializable;
import java.util.List;

public class TravelInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<TravelRoutineData> list_of_travel;
	UserData user;
	
	public List<TravelRoutineData> getList_of_travel() {
		return list_of_travel;
	}
	public void setList_of_travel(List<TravelRoutineData> list_of_travel) {
		this.list_of_travel = list_of_travel;
	}
	public UserData getUser() {
		return user;
	}
	public void setUser(UserData user) {
		this.user = user;
	}
	@Override
	public String toString() {
		String data =  "TravelInfo \nlist_of_travel= [\n"; 
		for(TravelRoutineData a:  list_of_travel) {
			data += a.toString();
		}
		data += "]\n[user=" + user.toString() + "]\n";
		return data;
	}
}
