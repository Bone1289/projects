package com.springboot.recipe.converers;

import com.springboot.recipe.commands.UnitOfMeasureCommand;
import com.springboot.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if (unitOfMeasure != null) {
            final UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
            unitOfMeasure.setId(unitOfMeasure.getId());
            unitOfMeasure.setDescription(unitOfMeasure.getDescription());
            return unitOfMeasureCommand;
        }

        return null;
    }
}
