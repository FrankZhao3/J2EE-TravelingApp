package com.wwfly.db.tables;
import java.util.Date;
import java.util.Set;

/**
 * TravelRoutine entity. @author MyEclipse Persistence Tools
 */

public class TravelRoutine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer travelId;
	private String name;
	private Date startDate;
	private Date endDate;
	private Double cost;
	private Integer numberOfRegistrations;
	private Integer maxRegistration;
	private String availability;
	private String destination;
	private String startingPlace;
	private Set<ViewRoutine> view_list;
	private Set<UserRoutine> userList;
	private Set<PhotoSharing> photoList;
	// Constructors

	/** default constructor */
	public TravelRoutine() {
	}

	/** full constructor */
	public TravelRoutine(String name, Date startDate, Date endDate,
			Double cost, Integer numberOfRegistrations,
			Integer maxRegistration, String availability) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.cost = cost;
		this.numberOfRegistrations = numberOfRegistrations;
		this.maxRegistration = maxRegistration;
		this.availability = availability;
	}

	// Property accessors

	public Integer getTravelId() {
		return this.travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getCost() {
		return this.cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getNumberOfRegistrations() {
		return this.numberOfRegistrations;
	}

	public void setNumberOfRegistrations(Integer numberOfRegistrations) {
		this.numberOfRegistrations = numberOfRegistrations;
	}

	public Integer getMaxRegistration() {
		return this.maxRegistration;
	}

	public void setMaxRegistration(Integer maxRegistration) {
		this.maxRegistration = maxRegistration;
	}

	public String getAvailability() {
		return this.availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Set<UserRoutine> getUserList() {
		return userList;
	}

	public void setUserList(Set<UserRoutine> userList) {
		this.userList = userList;
	}

	public String getStartingPlace() {
		return startingPlace;
	}

	public void setStartingPlace(String startingPlace) {
		this.startingPlace = startingPlace;
	}

	public Set<ViewRoutine> getView_list() {
		return view_list;
	}

	public void setView_list(Set<ViewRoutine> view_list) {
		this.view_list = view_list;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@Override
	public String toString() {
		return "TravelRoutine [name=" + name + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", cost=" + cost
				+ ", numberOfRegistrations=" + numberOfRegistrations
				+ ", maxRegistration=" + maxRegistration + ", availability="
				+ availability + ", destination=" + destination
				+ ", startingPlace=" + startingPlace + "]";
	}

	public Set<PhotoSharing> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(Set<PhotoSharing> photoList) {
		this.photoList = photoList;
	}

}