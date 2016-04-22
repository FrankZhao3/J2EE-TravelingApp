package com.wwfly.db.tables;

/**
 * ViewRoutineId entity. @author MyEclipse Persistence Tools
 */

public class ViewRoutineId implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer viewId;
	private Integer routineId;

	// Constructors

	/** default constructor */
	public ViewRoutineId() {
	}

	/** full constructor */
	public ViewRoutineId(Integer viewId, Integer routineId) {
		this.viewId = viewId;
		this.routineId = routineId;
	}

	// Property accessors

	public Integer getViewId() {
		return this.viewId;
	}

	public void setViewId(Integer viewId) {
		this.viewId = viewId;
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
		if (!(other instanceof ViewRoutineId))
			return false;
		ViewRoutineId castOther = (ViewRoutineId) other;

		return ((this.getViewId() == castOther.getViewId()) || (this
				.getViewId() != null && castOther.getViewId() != null && this
				.getViewId().equals(castOther.getViewId())))
				&& ((this.getRoutineId() == castOther.getRoutineId()) || (this
						.getRoutineId() != null && castOther.getRoutineId() != null && this
						.getRoutineId().equals(castOther.getRoutineId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getViewId() == null ? 0 : this.getViewId().hashCode());
		result = 37 * result
				+ (getRoutineId() == null ? 0 : this.getRoutineId().hashCode());
		return result;
	}

}