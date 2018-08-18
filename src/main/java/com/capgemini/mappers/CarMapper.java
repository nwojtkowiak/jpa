package com.capgemini.mappers;


import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CarMapper {

    public static CarTO toTO(CarEntity carEntity) {
        if (carEntity == null) {
            return null;
        }

        List<Long> loans = LoanMapper.map2TOs(carEntity.getLoans());

        return new CarTOBuilder().withCapacity(carEntity.getCapacity())
                .withId(carEntity.getId())
                .withColor(carEntity.getColor().getId())
                .withCourse(carEntity.getCourse())
                .withMark(carEntity.getMark())
                .withModel(carEntity.getModel())
                .withPower(carEntity.getPower())
                .withProdYear(carEntity.getProdYear())
                .withType(carEntity.getType().getId())
                .withLoans(loans)
                .build();
    }

    public static CarEntity toEntity(CarTO carTO) {
        if (carTO == null) {
            return null;
        }

        return new CarEntity(carTO.getId(), carTO.getMark(), carTO.getModel(), carTO.getProdYear(),
                carTO.getCapacity(), carTO.getPower(), carTO.getCourse());
    }

    public static List<Long> map2LongTOs(List<CarEntity> carEntities) {
        if (carEntities != null) {
            return carEntities.stream().map(CarEntity::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static List<CarTO> map2TOs(List<CarEntity> carEntities) {
        if (carEntities != null) {
            return carEntities.stream().map(CarMapper::toTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
