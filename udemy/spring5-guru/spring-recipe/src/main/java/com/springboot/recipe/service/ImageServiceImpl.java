package com.springboot.recipe.service;

import com.springboot.recipe.domain.Recipe;
import com.springboot.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile multipartFile) throws IOException {
        Recipe recipe = recipeRepository.findById(recipeId)
                .get();

        recipe.setImage(ArrayUtils.toObject(multipartFile.getBytes()));
        recipeRepository.save(recipe);
    }
}
