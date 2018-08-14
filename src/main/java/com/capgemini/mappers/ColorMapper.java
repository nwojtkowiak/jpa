package com.capgemini.mappers;

import com.capgemini.domain.ColorEntity;
import com.capgemini.types.ColorTO;

public class ColorMapper {
    public static ColorTO toTO(ColorEntity colorEntity) {
        if (colorEntity == null)
            return null;

        return new ColorTO(colorEntity.getName());

    }

    public static ColorEntity toEntity(ColorTO colorTO) {
        if (colorTO == null)
            return null;

        return new ColorEntity(colorTO.getName());

    }
}
