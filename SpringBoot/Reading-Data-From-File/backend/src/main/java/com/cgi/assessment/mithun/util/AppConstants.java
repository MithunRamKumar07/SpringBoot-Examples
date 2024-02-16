package com.cgi.assessment.mithun.util;

import java.util.Arrays;
import java.util.List;

/**
 * <p> File that contains the constants that are used across the app</p>
 */
public class AppConstants {

    public static final String RECIPE_PATH = "/recipes/";
    public static final String GET_ALL_RECIPES_PATH = "/getAll";
    public static final String GET_BY_INGREDIENTS_PATH = "/getByIngredients";
    public static final String LOG_ANALYSER_PATH = "/logAnalyser";
    public static final String APPLICATION_JSON = "application/json";
    public static final String GET_ALL_RECIPES = "getAllRecipes()";
    public static final String GET_RECIPES_BY_INGREDIENTS = "getRecipesByIngredients()";
    public static final String ANALYSE_LOG_STATEMENTS = "analyseLogStatements()";
    public static final String ANALYSE_PATH = "/analyse/{logLevel}";
    public static final String LOG_FILE_PATH = "classpath:logFile-2018-09-10.log";
    public static final String RECIPE_JSON_FILE_PATH = "classpath:receipe.json";
    public static final String NOT_APPLICABLE = "Not Applicable";
    public static final String ERROR_DURING_METHOD = "Error during Method";
    public static final int NUMBER_ONE = 1;
    public static final String CONTEXT_PATH = "/assessment/*";
    public static final String DEBUG = "DEBUG";
    public static final String INVALID_INPUT = "invalid";
    public static final List<String> ALLOWED_LOG_LEVELS = Arrays.asList("DEBUG","ERROR","FATAL","WARN","INFO");
    public static final String EXCEPTION = "Exception";
}
