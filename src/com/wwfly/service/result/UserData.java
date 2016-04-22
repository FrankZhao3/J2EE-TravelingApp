package com.wwfly.service.result;

import java.io.Serializable;
import java.util.Set;

public class UserData implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String name;
	private Integer age;
	private String sex;
	private String userEmail;
	private String userPhone;
	private String school;
	private Set<String> routineList;
	/** default constructor */
	public UserData() {
	}

	/** full constructor */
	public UserData(String name, Integer age, String sex, String userEmail,
			String userPhone) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	//To be edit
	@Override
	public String toString() {
		String data = "User [name=" + name + ", age=" + age
				+ ", sex=" + sex + ", userEmail=" + userEmail + ", userPhone="
				+ userPhone  + ", school=";
		if(school != null){
			data += school;
		}
		data += "]";
		return data;
	}

	public Set<String> getRoutineList() {
		return routineList;
	}

	public void setRoutineList(Set<String> routineList) {
		this.routineList = routineList;
	}
}
