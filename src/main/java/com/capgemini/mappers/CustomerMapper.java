package com.capgemini.mappers;

import com.capgemini.domain.CustomerEntity;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.CustomerTO.CustomerTOBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerTO toTO(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }


        return new CustomerTOBuilder()
                .withFirstName(customerEntity.getFirstName())
                .withLastName(customerEntity.getLastName())
                .withBirthDay(customerEntity.getBirthDay())
                .withAddress(customerEntity.getAddress().getId())
                .withCreditCard(customerEntity.getCreditCard())
                .withEmail(customerEntity.getEmail())
                .withPhoneNumber(customerEntity.getPhoneNumber())
                .withId(customerEntity.getId())
                .build();


    }

    public static CustomerEntity toEntity(CustomerTO customerTO) {
        if (customerTO == null) {
            return null;
        }

        return new CustomerEntity(customerTO.getId(), customerTO.getFirstName(), customerTO.getLastName(), customerTO.getEmail(),
                customerTO.getPhoneNumber(), customerTO.getBirthDay(), customerTO.getCreditCard());

    }

    public static List<Long> map2TOs(List<CustomerEntity> employeeEntities) {
        if (employeeEntities != null) {
            return employeeEntities.stream().map(CustomerEntity::getId).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
