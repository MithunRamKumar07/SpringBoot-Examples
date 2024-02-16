package com.cgi.assessment.mithun.controller;

import com.cgi.assessment.mithun.model.RecipeResponse;
import com.cgi.assessment.mithun.service.RecipeService;
import com.cgi.assessment.mithun.util.AppConstants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 *  Controller class that handles the logic to retrieve recipes. It has two endpoints
 *  <li>getAllRecipes</li>
 *  <li>getRecipesByIngredients</li>
 */
@Slf4j
@RestController
@RequestMapping(value = AppConstants.RECIPE_PATH)
@Validated
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    /**
     *  Gets all the recipes
     * @return All the recipes in the input file
     * @throws IOException
     */
    @GetMapping(produces= AppConstants.APPLICATION_JSON,value = AppConstants.GET_ALL_RECIPES_PATH)
    public ResponseEntity<RecipeResponse> getAllRecipes() throws IOException {
        log.info("Retrieving all the recipes");
        RecipeResponse recipes = recipeService.getAllRecipes();
        log.info("Successfully retrieved all the recipes");
        return ResponseEntity.ok()
                .body(recipes);
    }
    /**
     *  Gets the recipes that match the input ingredients
     * @param ingredients list of ingredients
     * @return Recipes that contain the input ingredients
     */
    @PostMapping(produces= AppConstants.APPLICATION_JSON,consumes = AppConstants.APPLICATION_JSON,value = AppConstants.GET_BY_INGREDIENTS_PATH)
    public ResponseEntity<RecipeResponse> getRecipesByIngredients(@RequestBody List<@Valid String> ingredients) {
        log.info("Retrieving all the recipes by Ingredients");
        RecipeResponse recipes = recipeService.getRecipesByIngredients(ingredients);
        log.info("Successfully retrieved all the recipes by Ingredients");
        return ResponseEntity.ok()
                .body(recipes);
    }
}
