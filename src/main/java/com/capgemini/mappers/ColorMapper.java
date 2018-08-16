package com.capgemini.mappers;

import com.capgemini.domain.ColorEntity;
import com.capgemini.types.ColorTO;
import com.capgemini.types.ColorTO.ColorToBuilder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ColorMapper {
    public static ColorTO toTO(ColorEntity colorEntity) {
        if (colorEntity == null)
            return null;

        return new ColorToBuilder()
                .withId(colorEntity.getId())
                .withName(colorEntity.getName())
                .build();

    }

    public static ColorEntity toEntity(ColorTO colorTO) {
        if (colorTO == null)
            return null;

        return new ColorEntity(null, colorTO.getName());

    }

    public static List<ColorTO> map2TOs(Collection<ColorEntity> colorEntities) {
        return colorEntities.stream().map(ColorMapper::toTO).collect(Collectors.toList());
    }
}
