package com.wwfly.db.tables;

/**
 * UserRoutineId entity. @author MyEclipse Persistence Tools
 */

public class UserRoutineId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer routineId;

	// Constructors

	/** default constructor */
	public UserRoutineId() {
	}

	/** full constructor */
	public UserRoutineId(Integer userId, Integer routineId) {
		this.userId = userId;
		this.routineId = routineId;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoutineId() {
		return this.routineId;
	}

	public void setRoutineId(Integer routineId) {
		this.routineId = routineId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoutineId))
			return false;
		UserRoutineId castOther = (UserRoutineId) other;

		return ((this.getUserId() == castOther.getUserId()) || (this
				.getUserId() != null && castOther.getUserId() != null && this
				.getUserId().equals(castOther.getUserId())))
				&& ((this.getRoutineId() == castOther.getRoutineId()) || (this
						.getRoutineId() != null
						&& castOther.getRoutineId() != null && this
						.getRoutineId().equals(castOther.getRoutineId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result
				+ (getRoutineId() == null ? 0 : this.getRoutineId().hashCode());
		return result;
	}

}