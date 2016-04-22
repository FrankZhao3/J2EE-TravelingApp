package com.wwfly.db.tables;

import java.util.Set;

/**
 * ViewSpot entity. @author MyEclipse Persistence Tools
 */

public class ViewSpot implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer spotId;
	private String spotName;
	private Integer reviewStars;
	private String reviews;
	private Integer cityId;
	private City location;
	private Set<ViewRoutine> routine_list;
	// Constructors

	/** default constructor */
	public ViewSpot() {
	}

	/** minimal constructor */
	public ViewSpot(Integer cityId) {
		this.cityId = cityId;
	}

	/** full constructor */
	public ViewSpot(String spotName, Integer reviewStars, String reviews,
			Integer cityId) {
		this.spotName = spotName;
		this.reviewStars = reviewStars;
		this.reviews = reviews;
		this.cityId = cityId;
	}

	// Property accessors

	public Integer getSpotId() {
		return this.spotId;
	}

	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}

	public String getSpotName() {
		return this.spotName;
	}

	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}

	public Integer getReviewStars() {
		return this.reviewStars;
	}

	public void setReviewStars(Integer reviewStars) {
		this.reviewStars = reviewStars;
	}

	public String getReviews() {
		return this.reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public City getLocation() {
		return location;
	}

	public void setLocation(City location) {
		this.location = location;
	}

	public Set<ViewRoutine> getRoutine_list() {
		return routine_list;
	}

	public void setRoutine_list(Set<ViewRoutine> routine_list) {
		this.routine_list = routine_list;
	}
	
	@Override
	public String toString() {
		return "ViewSpot [spotId=" + spotId + ", spotName=" + spotName
				+ ", reviewStars=" + reviewStars + ", reviews=" + reviews
				+ ", cityId=" + cityId + ", location=" + location + "]";
	}
}