package com.wwfly.service.result;

import java.io.Serializable;

public class Log implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "Log [message=" + message + ", errorMsg=" + errorMsg + "]";
	}
	private String message;
	private String errorMsg;
	private boolean hasError = false;
	public Log() {
		message = "";
		errorMsg = "";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMsg() {
		hasError = true;
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		hasError = true;
		this.errorMsg = errorMsg;
	}	
	public void appendMsg(String message ) {
		this.message += " ";
		this.message += message;
	}
	public void appendErrorMsg(String message) {
		hasError = true;
		this.errorMsg += " ";
		this.errorMsg += message;
	}
	public boolean hasError() {
		return hasError;
	}
}
