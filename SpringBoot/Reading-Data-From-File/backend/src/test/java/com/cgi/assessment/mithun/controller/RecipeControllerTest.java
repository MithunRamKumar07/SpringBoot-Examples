package com.cgi.assessment.mithun.controller;

import com.cgi.assessment.mithun.model.RecipeResponse;
import com.cgi.assessment.mithun.service.RecipeService;
import com.cgi.assessment.mithun.service.RecipeServiceTest;
import com.cgi.assessment.mithun.util.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/** Test class for {@link RecipeController }
 *
 */
@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

    @InjectMocks
    RecipeController recipeController;
    @Mock
    RecipeService recipeService;

    @Mock
    RecipeResponse recipeResponse;

    @Test
    public void testGetAllRecipes() throws IOException {
        when(recipeService.getAllRecipes()).thenReturn(recipeResponse);
        ResponseEntity<RecipeResponse> response = recipeController.getAllRecipes();
        assertNotNull(response);
    }

    @Test
    public void testGetRecipesByIngredients() throws IOException {
        when(recipeService.getRecipesByIngredients(any())).thenReturn(recipeResponse);
        ResponseEntity<RecipeResponse> response = recipeController.getRecipesByIngredients(Arrays.asList("onion","butter"));
        assertNotNull(response);
    }

}
