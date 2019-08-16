package com.springboot.recipe.service;

import com.springboot.recipe.commands.IngredientCommand;
import com.springboot.recipe.commands.UnitOfMeasureCommand;
import com.springboot.recipe.converers.IngredientCommandToIngredient;
import com.springboot.recipe.converers.IngredientToIngredientCommand;
import com.springboot.recipe.converers.UnitOfMeasureCommandToUnitOfMeasure;
import com.springboot.recipe.converers.UnitOfMeasureToUnitOfMeasureCommand;
import com.springboot.recipe.domain.Ingredient;
import com.springboot.recipe.domain.Recipe;
import com.springboot.recipe.repositories.IngredientRepository;
import com.springboot.recipe.repositories.RecipeRepository;
import com.springboot.recipe.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    public static final String TEST_INGREDIENT_DESCRIPTION = "Test Ingredient";
    public static final BigDecimal INGREDIENT_AMOUNT = new BigDecimal(2);
    IngredientToIngredientCommand ingredientToIngredientCommand;

    IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    RecipeRepository recipeRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, ingredientRepository, unitOfMeasureRepository);
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

    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setDescription("Each");
        unitOfMeasureCommand.setId(1L);

        IngredientCommand command = new IngredientCommand();
        command.setDescription(TEST_INGREDIENT_DESCRIPTION);
        command.setAmount(INGREDIENT_AMOUNT);
        command.setUnitOfMeasure(unitOfMeasureCommand);
        command.setRecipeId(2L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(ingredientCommandToIngredient.convert(command));

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        assertEquals(TEST_INGREDIENT_DESCRIPTION, savedCommand.getDescription());
        assertEquals(unitOfMeasureCommand.getDescription(), savedCommand.getUnitOfMeasure().getDescription());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));

    }

    @Test
    public void testDeleteById() throws Exception {
        //given
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //when
        ingredientService.deleteById(1L, 3L);

        //then
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}