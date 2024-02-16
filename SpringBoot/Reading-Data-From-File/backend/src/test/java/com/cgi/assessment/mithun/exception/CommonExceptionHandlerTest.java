package com.cgi.assessment.mithun.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** <p> Test class for {@link CommonExceptionHandler }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class CommonExceptionHandlerTest {

    @InjectMocks
    CommonExceptionHandler commonExceptionHandler;

    @Test
    public void testRecipeException(){
        RecipeException recipeException = new RecipeException("Exception",HttpStatus.BAD_REQUEST);
        ResponseEntity<ErrorResponse> response = commonExceptionHandler.handleRecipeException(recipeException);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    public void testLogAnalyserException(){
        LogAnalyserException logAnalyserException = new LogAnalyserException("Exception",HttpStatus.BAD_REQUEST,"AdditionalInfo");
        ResponseEntity<ErrorResponse> response = commonExceptionHandler.handleLogAnalyserException(logAnalyserException);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    public void testInvalidRequestException(){
        ResponseEntity<ErrorResponse> response = commonExceptionHandler.handleInvalidRequestException(new HttpMessageNotReadableException(""));
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    @Test
    public void testException(){
        ResponseEntity<ErrorResponse> response = commonExceptionHandler.handleException(new Exception());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

}
