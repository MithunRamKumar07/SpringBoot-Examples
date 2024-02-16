package com.cgi.assessment.mithun.logging;

import com.cgi.assessment.mithun.exception.LogAnalyserException;
import com.cgi.assessment.mithun.exception.RecipeException;
import com.cgi.assessment.mithun.model.LogAnalyserResponse;
import com.cgi.assessment.mithun.model.RecipeResponse;
import com.cgi.assessment.mithun.util.AppConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/** <p> Test for {@link PerformanceLogger }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class PerformanceLoggerTest {

    @InjectMocks
    PerformanceLogger performanceLogger;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    ResponseEntity<RecipeResponse> recipeResponse;

    @Mock
    ResponseEntity<LogAnalyserResponse> logAnalyserResponse;

    @Test
    public void testAroundGetAllRecipes() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenReturn(recipeResponse);
        assertDoesNotThrow(()->performanceLogger.aroundGetAllRecipes(proceedingJoinPoint));
    }

    @Test
    public void testAroundGetRecipesByIngredients() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenReturn(recipeResponse);
        assertDoesNotThrow(()->performanceLogger.aroundGetRecipesByIngredients(proceedingJoinPoint));
    }

    @Test
    public void testLogAnalyser() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenReturn(logAnalyserResponse);
        assertDoesNotThrow(()->performanceLogger.aroundLogAnalyser(proceedingJoinPoint));
    }

    @Test
    public void testLogAnalyserException() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(new LogAnalyserException(AppConstants.EXCEPTION, BAD_REQUEST,"Error"));
        assertThrows(LogAnalyserException.class, ()
                -> performanceLogger.aroundLogAnalyser(proceedingJoinPoint));
    }

    @Test
    public void testGetAllRecipesException() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(new RecipeException(AppConstants.EXCEPTION, BAD_REQUEST));
        assertThrows(RecipeException.class, ()
                -> performanceLogger.aroundGetAllRecipes(proceedingJoinPoint));
    }

    @Test
    public void testGetRecipesByIngredientException() throws Throwable {
        when(proceedingJoinPoint.proceed()).thenThrow(new RecipeException(AppConstants.EXCEPTION, BAD_REQUEST));
        assertThrows(RecipeException.class, ()
                -> performanceLogger.aroundGetRecipesByIngredients(proceedingJoinPoint));
    }
}
