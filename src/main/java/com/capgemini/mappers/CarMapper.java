package com.capgemini.mappers;


import com.capgemini.domain.CarEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.ColorTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.TypeTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CarMapper {

	public static CarTO toTO(CarEntity carEntity) {
		if (carEntity == null) {
            return null;
        }

		//Collection<EmployeeTO> keepers = EmployeeMapper.map2TOs(carEntity.getKeepers());
		ColorTO colorTO = ColorMapper.toTO(carEntity.getColor());
		TypeTO typeTO = TypeMapper.toTO(carEntity.getType());

        return new CarTOBuilder().withCapacity(carEntity.getCapacity())
				.withId(carEntity.getId())
				.withColor(colorTO)
				.withCourse(carEntity.getCourse())
				.withMark(carEntity.getMark())
				.withModel(carEntity.getModel())
				.withPower(carEntity.getPower())
				.withProdYear(carEntity.getProdYear())
				.withType(typeTO)
				//.withKeepers(keepers)
				.build();
	}

	public static CarEntity toEntity(CarTO carTO) {
		if (carTO == null) {
            return null;
        }

		return new CarEntity(carTO.getId(),carTO.getMark(),carTO.getModel(), carTO.getProdYear(),
                carTO.getCapacity(), carTO.getPower(), carTO.getCourse(),
                ColorMapper.toEntity(carTO.getColor()),
                TypeMapper.toEntity(carTO.getType()));
	}

	public static List<CarTO> map2TOs(Collection<CarEntity> carEntities) {
		if(carEntities != null) {
			return carEntities.stream().map(CarMapper::toTO).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}



}
