package com.capgemini.mappers;

import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.AddressTO;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.OfficeTO.OfficeTOBuilder;

public class OfficeMapper {
//TODO przy tworzeniu TO musi być builder, ale przy tworzeniu encji musi być konstruktor
//TODO embedded użyć choć raz -> Michał
//TODO

    public static OfficeTO toTO(OfficeEntity officeEntity) {
        if (officeEntity == null)
            return null;

        AddressTO addressTO = AddressMapper.toTO(officeEntity.getAddress());

        return new OfficeTOBuilder().withAddress(addressTO)
                .withName(officeEntity.getName())
                .withPhoneNumber(officeEntity.getPhoneNumber()).build();

    }

    public static OfficeEntity toEntity(OfficeTO officeTO) {
        if (officeTO == null)
            return null;

        AddressEntity addressEntity = AddressMapper.toEntity(officeTO.getAddress());
        return new OfficeEntity(officeTO.getName(), officeTO.getPhoneNumber(), addressEntity);

    }


}
