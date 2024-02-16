package com.cgi.assessment.mithun.logging;

import com.cgi.assessment.mithun.model.LogAnalyserResponse;
import com.cgi.assessment.mithun.model.RecipeResponse;
import com.cgi.assessment.mithun.util.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Monitors the performance of the APIs
 */
@Aspect
@Slf4j
@Component
@EnableAspectJAutoProxy
@Profile("performanceLogging")
public class PerformanceLogger {

    @Around("execution(* com.cgi.assessment.mithun.controller.RecipeController.getAllRecipes(..))")
    public ResponseEntity<RecipeResponse> aroundGetAllRecipes(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeInMillis = System.currentTimeMillis();
        try {
            ResponseEntity<RecipeResponse> response = (ResponseEntity<RecipeResponse>) joinPoint.proceed();
            logPerformance(AppConstants.GET_ALL_RECIPES, startTimeInMillis);
            return response;
        } catch (Throwable throwable) {
            log.debug("Error occurred during method : {}. Error Details:{}" ,AppConstants.GET_ALL_RECIPES, throwable.getMessage());
            throw throwable;
        }
    }

    @Around("execution(* com.cgi.assessment.mithun.controller.RecipeController.getRecipesByIngredients(..))")
    public ResponseEntity<RecipeResponse> aroundGetRecipesByIngredients(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeInMillis = System.currentTimeMillis();
        try {
            ResponseEntity<RecipeResponse> response = (ResponseEntity<RecipeResponse>) joinPoint.proceed();
            logPerformance(AppConstants.GET_RECIPES_BY_INGREDIENTS, startTimeInMillis);
            return response;
        } catch (Throwable throwable) {
            log.debug("Error occurred during method : {}. Error Details:{}" ,AppConstants.GET_RECIPES_BY_INGREDIENTS,throwable.getMessage());
            throw throwable;
        }
    }
    @Around("execution(* com.cgi.assessment.mithun.controller.LogAnalyserController.analyseLogs(..))")
    public ResponseEntity<LogAnalyserResponse> aroundLogAnalyser(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeInMillis = System.currentTimeMillis();
        try {
            ResponseEntity<LogAnalyserResponse> response = (ResponseEntity<LogAnalyserResponse>) joinPoint.proceed();
            logPerformance(AppConstants.ANALYSE_LOG_STATEMENTS, startTimeInMillis);
            return response;
        } catch (Throwable throwable) {
            log.debug("Error occurred during method : {}. Error Details:{}" ,AppConstants.ANALYSE_LOG_STATEMENTS,throwable.getMessage());
            throw throwable;
        }
    }

    private void logPerformance(String methodName, Long startTime){
        log.info("Performance Details for {} : responseTime - {} ms",methodName,System.currentTimeMillis() - startTime);
    }

}
