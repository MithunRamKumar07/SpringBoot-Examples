package com.cgi.assessment.mithun.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Exception for LogAnalyser Endpoint
 */
@Data
@AllArgsConstructor
public class LogAnalyserException extends RuntimeException {

    String exceptionMessage;
    HttpStatus httpStatus;
    String additionalInformation;

}
