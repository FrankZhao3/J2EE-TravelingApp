package com.wwfly.db.tables;
import java.util.Date;

/**
 * UserRoutine entity. @author MyEclipse Persistence Tools
 */

public class UserRoutine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserRoutineId id;
	private Date regTime;
	private User user;
	private TravelRoutine routine;
	// Constructors

	/** default constructor */
	public UserRoutine() {
	}

	/** minimal constructor */
	public UserRoutine(UserRoutineId id) {
		this.id = id;
	}

	/** full constructor */
	public UserRoutine(UserRoutineId id, Date regTime) {
		this.id = id;
		this.regTime = regTime;
	}

	// Property accessors

	public UserRoutineId getId() {
		return this.id;
	}

	public void setId(UserRoutineId id) {
		this.id = id;
	}

	public Date getRegTime() {
		return this.regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TravelRoutine getRoutine() {
		return routine;
	}

	public void setRoutine(TravelRoutine routine) {
		this.routine = routine;
	}

}