package com.capgemini.mappers;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.*;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class EmployeeMapper {
    public static EmployeeTO toTO(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }
        Collection<CarTO> cars = CarMapper.map2TOs(employeeEntity.getCars());
        AddressTO addressTO = AddressMapper.toTO(employeeEntity.getAddress());
        OfficeTO officeTO = OfficeMapper.toTO(employeeEntity.getOffice());
        PositionTO positionTO = PositionMapper.toTO(employeeEntity.getPosition());

        return new EmployeeTOBuilder().withFirstName(employeeEntity.getFirstName())
                .withId(employeeEntity.getId())
                .withLastName(employeeEntity.getLastName())
                .withBirthDay(employeeEntity.getBirthDay())
                .withAddress(addressTO)
                .withOffice(officeTO)
                .withPosition(positionTO)
                .withCars(cars).build();

        /*EmployeeTO(employeeEntity.getFirstName(),employeeEntity.getLastName(),
                employeeEntity.getBirthDay(),AddressMapper.toTO(employeeEntity.getAddress()),
                OfficeMapper.toTO(employeeEntity.getOffice()),PositionMapper.toTO(employeeEntity.getPosition()));*/

    }

    public static EmployeeEntity toEntity(EmployeeTO employeeTO) {
        if (employeeTO == null) {
            return null;
        }

        return new EmployeeEntity(employeeTO.getFirstName(),employeeTO.getLastName(),
                employeeTO.getBirthDay(),AddressMapper.toEntity(employeeTO.getAddress()),
                OfficeMapper.toEntity(employeeTO.getOffice()),PositionMapper.toEntity(employeeTO.getPosition()));

    }

    public static List<EmployeeTO> map2TOs(Collection<EmployeeEntity> employeeEntities) {
        if(employeeEntities != null) {
            return employeeEntities.stream().map(EmployeeMapper::toTO).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
