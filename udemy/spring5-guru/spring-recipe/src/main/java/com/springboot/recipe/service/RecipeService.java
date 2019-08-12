package com.springboot.recipe.service;

import com.springboot.recipe.commands.RecipeCommand;
import com.springboot.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteById(Long recipeId);
}
