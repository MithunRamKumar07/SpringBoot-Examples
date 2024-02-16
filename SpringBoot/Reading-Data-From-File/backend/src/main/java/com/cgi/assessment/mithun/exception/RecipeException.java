package com.cgi.assessment.mithun.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception for Receipe endpoint
 */
@AllArgsConstructor
public class RecipeException extends RuntimeException{
    @Getter
    String exceptionMessage;
    @Getter
    HttpStatus httpStatus;

}
