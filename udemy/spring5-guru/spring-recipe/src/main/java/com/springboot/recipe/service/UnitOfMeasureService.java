package com.springboot.recipe.service;

import com.springboot.recipe.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listOfUOMs();
}
