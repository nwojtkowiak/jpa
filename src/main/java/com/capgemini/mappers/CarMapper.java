package com.capgemini.mappers;


import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;

import java.util.List;
import java.util.stream.Collectors;

//TODO dodać keepers
public class CarMapper {

	public static CarTO toTO(CarEntity carEntity) {
		if (carEntity == null) {
            return null;
        }

        return new CarTO(carEntity.getMark(),carEntity.getModel(), carEntity.getProdYear(),
                carEntity.getCapacity(), carEntity.getPower(), carEntity.getCourse(),
                ColorMapper.toTO(carEntity.getColor()),
                TypeMapper.toTO(carEntity.getType()));
	}

	public static CarEntity toEntity(CarTO carTO) {
		if (carTO == null) {
            return null;
        }

		return new CarEntity(carTO.getMark(),carTO.getModel(), carTO.getProdYear(),
                carTO.getCapacity(), carTO.getPower(), carTO.getCourse(),
                ColorMapper.toEntity(carTO.getColor()),
                TypeMapper.toEntity(carTO.getType()));
	}

	public static List<CarTO> map2TOs(List<CarEntity> carEntities) {
		return carEntities.stream().map(CarMapper::toTO).collect(Collectors.toList());
	}

	public static List<CarEntity> map2Entities(List<CarTO> bookTOs) {
		return bookTOs.stream().map(CarMapper::toEntity).collect(Collectors.toList());
	}

}
