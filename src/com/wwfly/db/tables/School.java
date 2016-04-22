package com.wwfly.db.tables;

import java.util.Set;

/**
 * School entity. @author MyEclipse Persistence Tools
 */

public class School implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	private String name;
	private Integer studentNum;
	private Set<User> listOfStudents;
	// Constructors

	/** default constructor */
	public School() {
	}

	/** minimal constructor */
	public School(String name) {
		this.name = name;
	}

	/** full constructor */
	public School(String name, Integer studentNum) {
		this.name = name;
		this.studentNum = studentNum;
	}

	// Property accessors

	public Integer getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStudentNum() {
		return this.studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public Set<User> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(Set<User> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", name=" + name
				+ ", studentNum=" + studentNum + "]";
	}

}