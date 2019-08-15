package com.springboot.recipe.service;

import com.springboot.recipe.commands.IngredientCommand;
import com.springboot.recipe.converers.IngredientCommandToIngredient;
import com.springboot.recipe.converers.IngredientToIngredientCommand;
import com.springboot.recipe.domain.Ingredient;
import com.springboot.recipe.domain.Recipe;
import com.springboot.recipe.repositories.IngredientRepository;
import com.springboot.recipe.repositories.RecipeRepository;
import com.springboot.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id not found. Id:" + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId()
                        .equals(ingredientId))
                .map(ingredientToIngredientCommand::convert)
                .findFirst();

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


    //    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
//        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
//
//        if (!recipeOptional.isPresent()) {
//            return new IngredientCommand();
//        } else {
//            Recipe recipe = recipeOptional.get();
//
//            Optional<Ingredient> ingredientOptional = recipe
//                    .getIngredients()
//                    .stream()
//                    .filter(ingredient -> ingredient.getId()
//                            .equals(command.getId()))
//                    .findFirst();
//            if (ingredientOptional.isPresent()) {
//                Ingredient ingredientFound = ingredientOptional.get();
//                ingredientFound.setDescription(command.getDescription());
//                ingredientFound.setAmount(command.getAmount());
//                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(command.getUnitOfMeasure()
//                                                                                          .getId())
//                                                         .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
//            } else {
//                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
//                recipe.addIngredient(ingredient);
//            }
//            Recipe savedRecipe = recipeRepository.save(recipe);
//
//            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients()
//                    .stream()
//                    .filter(recipeIngredients -> recipeIngredients.getId()
//                            .equals(command.getId()))
//                    .findFirst();
//
//            if (!savedIngredientOptional.isPresent()) {
//                //not totally safe... But best guess
//                savedIngredientOptional = savedRecipe.getIngredients()
//                        .stream()
//                        .filter(recipeIngredients -> recipeIngredients.getDescription()
//                                .equals(command.getDescription()))
//                        .filter(recipeIngredients -> recipeIngredients.getAmount()
//                                .equals(command.getAmount()))
//                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure()
//                                .getId()
//                                .equals(command.getUnitOfMeasure()
//                                                .getId()))
//                        .findFirst();
//            }
//
//            //to do check for fail
//            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
//        }
//    }
    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) throws Exception {
        if (command == null) {
            log.info("No command info sent.");
            return null;
        }
        if (command.getId() != null) {
            Optional<Ingredient> ingredientOptional = ingredientRepository.findById(command.getId());
            if (ingredientOptional.isPresent()) {
                return updateIngredient(command, ingredientOptional.get());
            } else {
                return saveIngredient(command);
            }
        } else {
            return saveIngredient(command);
        }
    }

    private IngredientCommand updateIngredient(IngredientCommand command, Ingredient ingredient) throws Exception {
        ingredient.setDescription(command.getDescription());
        ingredient.setAmount(command.getAmount());
        ingredient.setUnitOfMeasure(
                unitOfMeasureRepository.findById(command.getUnitOfMeasure()
                                                         .getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))
        );
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if (!recipeOptional.isPresent()) {
            throw new Exception("Recipe not found");
        }
        Recipe recipe = recipeOptional.get();
        recipe.addIngredient(ingredient);

        return findByIngredientId(command.getId());
    }

    private IngredientCommand saveIngredient(IngredientCommand command) throws Exception {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());
        if (!recipeOptional.isPresent()) {
            throw new Exception("Recipe not found");
        }
        Recipe recipe = recipeOptional.get();
        Ingredient ingredient = ingredientCommandToIngredient.convert(command);
        recipe.addIngredient(ingredient);
        Recipe savedRecipe = recipeRepository.save(recipe);
        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients()
                .stream()
                .filter(recipeIngredients ->
                                recipeIngredients.getDescription() != null &&
                                        recipeIngredients.getDescription()
                                                .equals(command.getDescription()))
                .filter(recipeIngredients -> recipeIngredients.getAmount() != null &&
                        recipeIngredients.getAmount()
                                .equals(command.getAmount()))
                .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure() != null &&
                        recipeIngredients.getUnitOfMeasure()
                                .getId()
                                .equals(command.getUnitOfMeasure()
                                                .getId()))
                .findFirst();

        return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
    }
}