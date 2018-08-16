package com.capgemini.mappers;

import com.capgemini.domain.PositionEntity;
import com.capgemini.types.PositionTO;
import com.capgemini.types.PositionTO.PositionToBuilder;

public class PositionMapper {
    public static PositionTO toTO(PositionEntity positionEntity) {
        if (positionEntity == null)
            return null;

        return new PositionToBuilder()
                .withId(positionEntity.getId())
                .withName(positionEntity.getName()).build();

    }

    public static PositionEntity toEntity(PositionTO colorTO) {
        if (colorTO == null)
            return null;

        return new PositionEntity(colorTO.getName());

    }
}
