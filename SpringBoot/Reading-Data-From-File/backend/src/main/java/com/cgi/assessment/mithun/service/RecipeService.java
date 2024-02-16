package com.cgi.assessment.mithun.service;

import com.cgi.assessment.mithun.exception.RecipeException;
import com.cgi.assessment.mithun.model.Recipe;
import com.cgi.assessment.mithun.model.RecipeResponse;
import com.cgi.assessment.mithun.util.FileUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
/**
 * <p> Service class to retrieve the recipes </p>
 * @author Mithun
 */
public class RecipeService {

    @Autowired
    ObjectMapper objectMapper;

    @Value("${assessment.recipe.file.path}")
    String recipeFilePath;
    /**
     * <p> Retrieves all the recipes </p>
     * @return all
     * @author Mithun
     */
    public RecipeResponse getAllRecipes() throws IOException {
        File file = FileUtils.readRecipeFile(recipeFilePath);
        List<Recipe> recipes = Arrays.asList(objectMapper.readValue(file, Recipe[].class));
        return new RecipeResponse(recipes);

    }

    /**
     * <p> Accepts list of ingredients and fetches the recipes based on the input</p>
     * @param ingredients
     * @return recipes that contain the input ingredients
     */
    public RecipeResponse getRecipesByIngredients(List<String> ingredients) {
        File file = FileUtils.readRecipeFile(recipeFilePath);
        List<Recipe> recipes = getRecipes(file);
        //Filter the recipes bases on input ingredients and sort them
        List<Recipe> filteredRecipes = recipes.stream()
                .filter(recipe-> Arrays.asList(recipe.getIngredients())
                        .stream()
                        .anyMatch(ingredient-> ingredients.contains(ingredient)))
                .sorted(Comparator.comparing(Recipe::getTitle))
                .toList();
        //Throw exception if the input ingredient is invalid
        if(filteredRecipes.size()==0){
            log.error("The input ingredient is not in the recipe list");
            throw new RecipeException("Please enter a valid ingredient. Your input doesn't match",
                    HttpStatus.BAD_REQUEST);
        }
        return new RecipeResponse(filteredRecipes);
    }

    /**
     *   Loads the file content and fetches the list of recipes
     * @param file
     * @return list of recipes
     */
    private List<Recipe> getRecipes(File file) {
        List<Recipe> recipes;
        try {
            recipes = Arrays.asList(objectMapper.readValue(file, Recipe[].class));
        } catch (IOException exception) {
            log.error("Exception occurred while retrieving the recipes from the file. Exception details: {}",
                    exception.getMessage());
            throw new RecipeException("Error occurred while retrieving the recipes",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return recipes;
    }
}
