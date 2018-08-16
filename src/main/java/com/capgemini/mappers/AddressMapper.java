package com.capgemini.mappers;

import com.capgemini.domain.AddressEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.AddressTO.AddressTOBuilder;

public class AddressMapper {
    public static AddressTO toTO(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        return new AddressTOBuilder().withBuilding(addressEntity.getBuilding())
               // .withId(addressEntity.getId())
                .withStreet(addressEntity.getStreet())
                .withCity(addressEntity.getCity())
                .withPostCode(addressEntity.getPost_code())
                .withFlat(addressEntity.getFlat()).build();

    }

    public static AddressEntity toEntity(AddressTO addressTO) {
        if (addressTO == null) {
            return null;
        }

        return new AddressEntity(addressTO.getStreet(),addressTO.getBuilding(),
                addressTO.getFlat(),addressTO.getPost_code(),addressTO.getCity());
    }
}
