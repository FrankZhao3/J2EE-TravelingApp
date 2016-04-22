package com.wwfly.db.tables;

import java.util.Set;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

/**
 * @author FrankZhao
 *
 */
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String name;
	private Integer age;
	private String sex;
	private String userEmail;
	private String userPhone;
	private School school;
	private Integer UserSchoolId;
	private Set<UserRoutine> routineList;
	private Set<PhotoSharing> photoList;
	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, Integer age, String sex, String userEmail,
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Set<UserRoutine> getRoutineList() {
		return routineList;
	}

	public void setRoutineList(Set<UserRoutine> routineList) {
		this.routineList = routineList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + ", userEmail=" + userEmail + ", userPhone="
				+ userPhone + ", school=" + school.toString() + ", \nroutineList= " + routineList.toString() + "]";
	}

	public Integer getUserSchoolId() {
		return UserSchoolId;
	}

	public void setUserSchoolId(Integer userSchoolId) {
		UserSchoolId = userSchoolId;
	}

	public Set<PhotoSharing> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(Set<PhotoSharing> photoList) {
		this.photoList = photoList;
	}
	

}