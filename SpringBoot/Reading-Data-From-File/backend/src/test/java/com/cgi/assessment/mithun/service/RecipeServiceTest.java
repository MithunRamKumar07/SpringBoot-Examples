package com.cgi.assessment.mithun.service;

import com.cgi.assessment.mithun.exception.RecipeException;
import com.cgi.assessment.mithun.model.Recipe;
import com.cgi.assessment.mithun.model.RecipeResponse;
import com.cgi.assessment.mithun.util.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/** <p> Test class for {@link RecipeService }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @InjectMocks
    RecipeService recipeService;
    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(recipeService,"recipeFilePath", AppConstants.RECIPE_JSON_FILE_PATH);
    }

    @Test
    public void testGetAllRecipes() throws IOException {
        when(objectMapper.readValue((File) any(), eq(Recipe[].class))).thenReturn(getRecipes());
        RecipeResponse recipes = recipeService.getAllRecipes();
        assertNotNull(recipes);
        assertEquals(recipes.getRecipes().size(),3);
        assertEquals(Arrays.asList(getRecipes()).toString(),recipes.getRecipes().toString());
        verify(objectMapper,times(1)).readValue((File) any(), eq(Recipe[].class));
    }

    @Test
    public void testGetRecipesByIngredients() throws IOException {
        when(objectMapper.readValue((File) any(), eq(Recipe[].class))).thenReturn(getRecipes());
        RecipeResponse recipes = recipeService.getRecipesByIngredients(Arrays.asList("onions"));
        assertNotNull(recipes);
        assertEquals(recipes.getRecipes().size(),1);
        assertTrue(recipes.getRecipes().stream().anyMatch(recipe-> Arrays.asList(recipe.getIngredients()).contains("onions")));
        verify(objectMapper,times(1)).readValue((File) any(), eq(Recipe[].class));
    }

    @Test
    public void testGetRecipesByIngredientsForInvalidInput() throws IOException {
        when(objectMapper.readValue((File) any(), eq(Recipe[].class))).thenReturn(getRecipes());
        assertThrows(RecipeException.class,()->recipeService.getRecipesByIngredients(Arrays.asList("onion")));
        verify(objectMapper,times(1)).readValue((File) any(), eq(Recipe[].class));

    }

    @Test
    public void testGetRecipesForIOException() throws IOException {
        when(objectMapper.readValue((File) any(), eq(Recipe[].class))).thenThrow(IOException.class);
        assertThrows(RecipeException.class,()->recipeService.getRecipesByIngredients(Arrays.asList("onion")));
        verify(objectMapper,times(1)).readValue((File) any(), eq(Recipe[].class));
    }

    public static Recipe[] getRecipes(){
        Recipe[] recipeArray = new Recipe[3];
        recipeArray[0]=frameRecipes("Blue Ribbon Meatloaf","http://www.eatingwell.com/recipes/meatloaf.html",new String[]{"onions"},"\"http://img.recipepuppy.com/694321.jpg");
        recipeArray[1]=frameRecipes("Spaghetti with Clams & Corn","http://www.eatingwell.com/recipes/spaghetti_clams_corn.html",new String[]{"onions,butter"},"http://img.recipepuppy.com/698569.jpg");
        recipeArray[2]=frameRecipes("Pulled Chicken Sandwiches (Crock Pot)","http://www.recipezaar.com/Pulled-Chicken-Sandwiches-Crock-Pot-242547",new String[]{"chicken,butter"},"thumbnail\": \"http://img.recipepuppy.com/107122.jpg");
        return recipeArray;
    }

    private static Recipe frameRecipes(String title,String href,String[] ingredients,String thumbnail) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setHref(href);
        recipe.setIngredients(ingredients);
        recipe.setThumbnail(thumbnail);
        return recipe;
    }
}
