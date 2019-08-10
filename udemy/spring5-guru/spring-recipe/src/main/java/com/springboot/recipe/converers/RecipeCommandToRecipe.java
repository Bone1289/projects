package com.springboot.recipe.converers;

import com.springboot.recipe.commands.RecipeCommand;
import com.springboot.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter) {
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories()
                .size() > 0) {
            recipeCommand.getCategories()
                    .forEach(category -> recipe.getCategories()
                            .add(categoryConverter.convert(category)));
        }

        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients()
                .size() > 0) {
            recipeCommand.getIngredients()
                    .forEach(ingredient -> {
                        recipe.getIngredients()
                                .add(ingredientConverter.convert(ingredient));
                    });
        }

        return recipe;
    }
}
