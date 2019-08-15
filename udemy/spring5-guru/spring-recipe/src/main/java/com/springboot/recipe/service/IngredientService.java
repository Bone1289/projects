package com.springboot.recipe.service;

import com.springboot.recipe.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand findByIngredientId(Long ingredientId) throws Exception;

    IngredientCommand saveIngredientCommand(IngredientCommand command) throws Exception;

}
