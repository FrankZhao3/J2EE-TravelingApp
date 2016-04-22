package com.wwfly.db.tables;

/**
 * Login entity. @author MyEclipse Persistence Tools
 */

public class Login implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	// Constructors

	/** default constructor */
	public Login() {
	}

	/** minimal constructor */
	public Login(String userName) {
		this.userName = userName;
	}

	/** full constructor */
	public Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	// Property accessors

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}