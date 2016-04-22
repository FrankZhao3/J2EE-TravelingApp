package com.wwfly.service.param;
import java.io.Serializable;
import java.util.Date;

public class TravelRoutineParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Date startDate;
	private Date endDate;
	private Double cost;
	private Integer numberOfRegistrations;
	private Integer maxRegistration;
	private String availability;
	private String destination;
	private String startingPlace;
	
	public TravelRoutineParam() {
		
	}
	public TravelRoutineParam(String name, Date startDate,
			Date endDate, Double cost, Integer numberOfRegistrations,
			Integer maxRegistration, String availability, String destination,
			String startingPlace) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.numberOfRegistrations = numberOfRegistrations;
		this.maxRegistration = maxRegistration;
		this.availability = availability;
		this.destination = destination;
		this.startingPlace = startingPlace;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Integer getNumberOfRegistrations() {
		return numberOfRegistrations;
	}
	public void setNumberOfRegistrations(Integer numberOfRegistrations) {
		this.numberOfRegistrations = numberOfRegistrations;
	}
	public Integer getMaxRegistration() {
		return maxRegistration;
	}
	public void setMaxRegistration(Integer maxRegistration) {
		this.maxRegistration = maxRegistration;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getStartingPlace() {
		return startingPlace;
	}
	public void setStartingPlace(String startingPlace) {
		this.startingPlace = startingPlace;
	}
}
