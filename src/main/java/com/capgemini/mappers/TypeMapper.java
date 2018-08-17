package com.capgemini.mappers;

import com.capgemini.domain.TypeEntity;
import com.capgemini.types.TypeTO;
import com.capgemini.types.TypeTO.TypeToBuilder;

public class TypeMapper {
    public static TypeTO toTO(TypeEntity typeEntity) {
        if (typeEntity == null)
            return null;

        return new TypeToBuilder()
                .withId(typeEntity.getId())
                .withName(typeEntity.getName())
                .build();
    }

    public static TypeEntity toEntity(TypeTO typeTO) {
        if (typeTO == null)
            return null;

        return new TypeEntity(typeTO.getId(), typeTO.getName());
    }
}
