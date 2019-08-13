package com.springboot.recipe.service;

import com.springboot.recipe.commands.IngredientCommand;
import com.springboot.recipe.converers.IngredientToIngredientCommand;
import com.springboot.recipe.converers.UnitOfMeasureToUnitOfMeasureCommand;
import com.springboot.recipe.domain.Ingredient;
import com.springboot.recipe.domain.Recipe;
import com.springboot.recipe.repositories.IngredientRepository;
import com.springboot.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Autowired
    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository, ingredientRepository);
    }

    @Test
    public void findByRecipeIdAndIngredientId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.addIngredient(new Ingredient(1L));
        recipe.addIngredient(new Ingredient(2L));
        recipe.addIngredient(new Ingredient(3L));

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void findByIngredientId() throws Exception {
        Ingredient ingredient = new Ingredient(1L);
        Optional<Ingredient> ingredientOptional = Optional.of(ingredient);

        when(ingredientRepository.findById(anyLong())).thenReturn(ingredientOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByIngredientId(1L);

        //when
        assertEquals(Long.valueOf(1L), ingredientCommand.getId());
        verify(recipeRepository, never()).findById(anyLong());
        verify(ingredientRepository, times(1)).findById(anyLong());
    }
}