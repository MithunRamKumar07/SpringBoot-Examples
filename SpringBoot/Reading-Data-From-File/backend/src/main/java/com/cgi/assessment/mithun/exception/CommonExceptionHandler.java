package com.cgi.assessment.mithun.exception;

import com.cgi.assessment.mithun.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * Global Exception Handler
 */
@ControllerAdvice
@Component
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(LogAnalyserException.class)
    public final ResponseEntity<ErrorResponse> handleLogAnalyserException(LogAnalyserException logAnalyserException){
        return ResponseEntity
                .status(logAnalyserException.httpStatus)
                .body(frameErrorResponse(logAnalyserException.getExceptionMessage(),
                        logAnalyserException.getAdditionalInformation()));
    }

    @ExceptionHandler(RecipeException.class)
    public final ResponseEntity<ErrorResponse> handleRecipeException(RecipeException recipeException){
        return ResponseEntity
                .status(recipeException.httpStatus)
                .body(frameErrorResponse(recipeException.getExceptionMessage(),
                        AppConstants.NOT_APPLICABLE));

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidRequestException(HttpMessageNotReadableException httpMessageNotReadableException){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(frameErrorResponse("Please provide valid input",
                        "Input value must be only string or list of strings"));

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(frameErrorResponse((exception.getMessage()),
                        AppConstants.NOT_APPLICABLE));

    }

    private ErrorResponse frameErrorResponse(String errorDetails,String additionalInfo) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorDetails(errorDetails);
        errorResponse.setAdditionalInformation(additionalInfo);
        return errorResponse;
    }

}
