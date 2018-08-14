package com.capgemini.mappers;

import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.OfficeTO;

public class OfficeMapper {
//TODO przy tworzeniu TO musi być builder, ale przy tworzeniu encji musi być konstruktor
//TODO embedded użyć choć raz -> Michał
//TODO

    public static OfficeTO toTO(OfficeEntity officeEntity) {
        if (officeEntity == null)
            return null;

        return new OfficeTO(officeEntity.getName(), officeEntity.getPhoneNumber(), officeEntity.getAddress());

    }

    public static OfficeEntity toEntity(OfficeTO officeTO) {
        if (officeTO == null)
            return null;

        return new OfficeEntity(officeTO.getName(), officeTO.getPhoneNumber(), officeTO.getAddress());

    }


}
