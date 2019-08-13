package com.springboot.recipe.service;

import com.springboot.recipe.commands.IngredientCommand;
import com.springboot.recipe.converers.IngredientToIngredientCommand;
import com.springboot.recipe.domain.Ingredient;
import com.springboot.recipe.domain.Recipe;
import com.springboot.recipe.repositories.IngredientRepository;
import com.springboot.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found. Id:" + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.error("ingredient id not found. Id:" + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand findByIngredientId(Long ingredientId) throws Exception {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredientId);
        Ingredient ingredient = ingredientOptional.orElseThrow(() -> new Exception(MessageFormat.format("No Ingredient found for ID {0}", ingredientId)));
        return ingredientToIngredientCommand.convert(ingredient);
    }
}
