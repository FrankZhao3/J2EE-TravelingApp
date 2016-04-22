package com.wwfly.service.param;

import java.io.Serializable;

public class RegisterParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	private String sex;
	private String userEmail;
	private String userPhone;
	private String schoolName;
	
	//default constructor
	public RegisterParam() {
		
	}
	
	public RegisterParam(String name, Integer age, String sex,
			String userEmail, String userPhone) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.schoolName = null;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
