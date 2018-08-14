package com.capgemini.mappers;

import com.capgemini.domain.AddressEntity;
import com.capgemini.types.AddressTO;

public class AddressMapper {
    public static AddressTO toTO(AddressEntity addressEntity) {
        if (addressEntity == null) {
            return null;
        }

        return new AddressTO(addressEntity.getStreet(),addressEntity.getBuilding(),
                addressEntity.getFlat(),addressEntity.getPost_code(),addressEntity.getCity());
    }

    public static AddressEntity toEntity(AddressTO addressTO) {
        if (addressTO == null) {
            return null;
        }

        return new AddressEntity(addressTO.getStreet(),addressTO.getBuilding(),
                addressTO.getFlat(),addressTO.getPost_code(),addressTO.getCity());
    }
}
