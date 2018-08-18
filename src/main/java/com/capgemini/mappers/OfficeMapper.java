package com.capgemini.mappers;

import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.OfficeTO.OfficeTOBuilder;

public class OfficeMapper {

    public static OfficeTO toTO(OfficeEntity officeEntity) {
        if (officeEntity == null)
            return null;

        Long address;
        if (officeEntity.getAddress() != null) {
            address = officeEntity.getAddress().getId();
        } else {
            address = null;
        }

        return new OfficeTOBuilder().withAddress(address)
                .withId(officeEntity.getId())
                .withName(officeEntity.getName())
                .withPhoneNumber(officeEntity.getPhoneNumber()).build();

    }

    public static OfficeEntity toEntity(OfficeTO officeTO) {
        if (officeTO == null)
            return null;


        return new OfficeEntity(officeTO.getName(), officeTO.getPhoneNumber());

    }


}
