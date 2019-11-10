package com.cts.assignment.exception;

import org.springframework.http.HttpStatus;

public class RaboBankException extends Exception{

	private static final long serialVersionUID = -4280275809614237476L;
	
	private String exceptionMessage;
	private HttpStatus httpStatus;
	private String errorMessage;
	
	public RaboBankException() {
		
	}
	
	public RaboBankException(String exceptionMessage, HttpStatus httpStatus, String errorMessage) {
		super();
		this.exceptionMessage = exceptionMessage;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	
}
