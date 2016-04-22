package com.wwfly.db.tables;

/**
 * TravelCost entity. @author MyEclipse Persistence Tools
 */

public class TravelCost implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 2435995596750672249L;
	private Integer idTravelCost;
	private String startPlace;
	private String endPlace;
	private Double cost;

	// Constructors

	/** default constructor */
	public TravelCost() {
	}

	/** full constructor */
	public TravelCost(String startPlace, String endPlace, Double cost) {
		this.startPlace = startPlace;
		this.endPlace = endPlace;
		this.cost = cost;
	}

	// Property accessors

	public Integer getIdTravelCost() {
		return this.idTravelCost;
	}

	public void setIdTravelCost(Integer idTravelCost) {
		this.idTravelCost = idTravelCost;
	}

	public String getStartPlace() {
		return this.startPlace;
	}

	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}

	public String getEndPlace() {
		return this.endPlace;
	}

	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

}