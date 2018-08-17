package com.capgemini.dao;

import com.capgemini.domain.OfficeEntity;


public interface OfficeDao extends Dao<OfficeEntity, Long> {

    OfficeEntity add(OfficeEntity entity);

    void deleteOffice(long id);


}
