package com.wwfly.service.result;

import java.io.Serializable;
import java.sql.Timestamp;

import com.wwfly.db.tables.TravelRoutine;
import com.wwfly.db.tables.User;

public class PhotoSharingData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String photoUrl;
	private Timestamp recordTime;
	private String userReview;
	private String user;
	private String travelRoutine;

	// Constructors
	public PhotoSharingData(String photoUrl, Timestamp recordTime,
			String userReview) {
		super();
		this.photoUrl = photoUrl;
		this.recordTime = recordTime;
		this.userReview = userReview;
	}
	/** default constructor */
	public PhotoSharingData() {
	}

	// Property accessors

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTravelRoutine() {
		return travelRoutine;
	}

	public void setTravelRoutine(String travelRoutine) {
		this.travelRoutine = travelRoutine;
	}

	@Override
	public String toString() {
		return "PhotoSharingData [photoUrl=" + photoUrl + ", recordTime="
				+ recordTime + ", userReview=" + userReview + ", user=" + user
				+ ", travelRoutine=" + travelRoutine + "]";
	}
}
