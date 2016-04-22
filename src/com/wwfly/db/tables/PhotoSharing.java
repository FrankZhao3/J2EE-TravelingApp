package com.wwfly.db.tables;

import java.sql.Timestamp;

/**
 * PhotoSharing entity. @author MyEclipse Persistence Tools
 */

public class PhotoSharing implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer photoId;
	private String photoUrl;
	private Timestamp recordTime;
	private String userReview;
	private Integer travelRoutineId;
	private Integer userId;
	private User user;
	private TravelRoutine travelRoutine;
	// Constructors

	/** default constructor */
	public PhotoSharing() {
	}

	/** full constructor */
	public PhotoSharing(String photoUrl, Timestamp recordTime,
			String userReview, Integer travelRoutineId, Integer userId) {
		this.photoUrl = photoUrl;
		this.recordTime = recordTime;
		this.userReview = userReview;
		this.travelRoutineId = travelRoutineId;
		this.userId = userId;
	}

	// Property accessors

	public Integer getPhotoId() {
		return this.photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Timestamp getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public String getUserReview() {
		return this.userReview;
	}

	public void setUserReview(String userReview) {
		this.userReview = userReview;
	}

	public Integer getTravelRoutineId() {
		return this.travelRoutineId;
	}

	public void setTravelRoutineId(Integer travelRoutineId) {
		this.travelRoutineId = travelRoutineId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TravelRoutine getTravelRoutine() {
		return travelRoutine;
	}

	public void setTravelRoutine(TravelRoutine travelRoutine) {
		this.travelRoutine = travelRoutine;
	}

}