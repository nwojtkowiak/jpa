package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;

import java.util.List;


public interface OfficeDao extends Dao<OfficeEntity, Long> {

    OfficeEntity add(OfficeEntity entity);

    void deleteOffice(long id);


}
