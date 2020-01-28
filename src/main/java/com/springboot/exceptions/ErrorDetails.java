package com.springboot.exceptions;

import java.util.Date;

/**
 * @author My PC
 *
 */
public class ErrorDetails {

	private Date timestamp;
	private int errorCode;
	private String message;
	private String description;
	public ErrorDetails() {
	}

	public ErrorDetails(int errorCode,String message){
		this.errorCode=errorCode;
		this.message=message;
	}

	public ErrorDetails(String message,String description,int errorCode){
		this.description=description;
		this.message=message;
		this.errorCode= errorCode;
	}

	public ErrorDetails(String message,String description){
		this.description=description;
		this.message=message;
	}

	public ErrorDetails(Date timestamp, String message,String description,int errorCode){
		this.timestamp = timestamp;
		this.description=description;
		this.message=message;
		this.errorCode= errorCode;
	}
	public ErrorDetails(Date timestamp, String message,String description){
		this.timestamp = timestamp;
		this.description=description;
		this.message=message;
	}



	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
