package com.cts.assignment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Mithun
 * 
 * <p>Custom Exception Handler for all the exceptions that occur across the application
 * All the exceptions are grouped under one common Exception: {@link RaboBankException.java}</p>
 *
 */
@RestControllerAdvice
public class RaboBankExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RaboBankException.class)
	public ResponseEntity<Object> handleRaboBankException(RaboBankException exception) {
		return new ResponseEntity<Object>("Error Description : " + exception.getErrorMessage()
				+ "\n Exception Message : " + exception.getExceptionMessage(), exception.getHttpStatus());
	}
}
