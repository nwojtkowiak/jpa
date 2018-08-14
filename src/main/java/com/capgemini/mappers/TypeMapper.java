package com.capgemini.mappers;

import com.capgemini.domain.TypeEntity;
import com.capgemini.types.TypeTO;

public class TypeMapper {
    public static TypeTO toTO(TypeEntity typeEntity) {
        if (typeEntity == null)
            return null;

        return new TypeTO(typeEntity.getName());
    }

    public static TypeEntity toEntity(TypeTO typeTO) {
        if (typeTO == null)
            return null;

        return new TypeEntity(typeTO.getName());
    }
}
