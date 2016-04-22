package com.wwfly.db.tables;

/**
 * ViewRoutine entity. @author MyEclipse Persistence Tools
 */

public class ViewRoutine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewRoutineId id;
	private ViewSpot viewSpot;
	private TravelRoutine travelRoutine;
	
	// Constructors

	/** default constructor */
	public ViewRoutine() {
	}

	/** full constructor */
	public ViewRoutine(ViewRoutineId id) {
		this.id = id;
	}

	// Property accessors

	public ViewRoutineId getId() {
		return this.id;
	}

	public void setId(ViewRoutineId id) {
		this.id = id;
	}

	public ViewSpot getViewSpot() {
		return viewSpot;
	}

	public void setViewSpot(ViewSpot viewSpot) {
		this.viewSpot = viewSpot;
	}

	public TravelRoutine getTravelRoutine() {
		return travelRoutine;
	}

	public void setTravelRoutine(TravelRoutine travelRoutine) {
		this.travelRoutine = travelRoutine;
	}

}