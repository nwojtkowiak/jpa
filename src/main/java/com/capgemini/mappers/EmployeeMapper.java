package com.capgemini.mappers;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeTO;

//TODO dodać cars
public class EmployeeMapper {
    public static EmployeeTO toTO(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }

        return new EmployeeTO(employeeEntity.getFirstName(),employeeEntity.getLastName(),
                employeeEntity.getBirthDay(),AddressMapper.toTO(employeeEntity.getAddress()),
                OfficeMapper.toTO(employeeEntity.getOffice()),PositionMapper.toTO(employeeEntity.getPosition()));

    }

    public static EmployeeEntity toEntity(EmployeeTO employeeTO) {
        if (employeeTO == null) {
            return null;
        }

        return new EmployeeEntity(employeeTO.getFirstName(),employeeTO.getLastName(),
                employeeTO.getBirthDay(),AddressMapper.toEntity(employeeTO.getAddress()),
                OfficeMapper.toEntity(employeeTO.getOffice()),PositionMapper.toEntity(employeeTO.getPosition()));

    }
}
