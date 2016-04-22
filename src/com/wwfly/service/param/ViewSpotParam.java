package com.wwfly.service.param;

import java.io.Serializable;

public class ViewSpotParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String spotName;
	private Integer reviewStars;
	private String reviews;
	private String cityName;
	
	public ViewSpotParam(String spotName, Integer reviewStars, String reviews,
			String cityName) {
		super();
		this.spotName = spotName;
		this.reviewStars = reviewStars;
		this.reviews = reviews;
		this.cityName = cityName;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public Integer getReviewStars() {
		return reviewStars;
	}
	public void setReviewStars(Integer reviewStars) {
		this.reviewStars = reviewStars;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
