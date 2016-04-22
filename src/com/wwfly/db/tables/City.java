package com.wwfly.db.tables;

import java.util.Set;

/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7386785726534640823L;
	private Integer id;
	private Integer cityIndex;
	private Integer provinceId;
	private String name;
	private Set<ViewSpot> view_list;
	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(Integer cityIndex, Integer provinceId, String name) {
		this.cityIndex = cityIndex;
		this.provinceId = provinceId;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityIndex() {
		return this.cityIndex;
	}

	public void setCityIndex(Integer cityIndex) {
		this.cityIndex = cityIndex;
	}

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ViewSpot> getView_list() {
		return view_list;
	}

	public void setView_list(Set<ViewSpot> view_list) {
		this.view_list = view_list;
	}
	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}

}