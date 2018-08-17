package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {


    @Override
    public EmployeeEntity add(EmployeeEntity entity) {
        return save(entity);
    }

    @Override
    public void deleteEmployee(long id) {
        delete(id);
    }

    @Override
    public EmployeeEntity updateEmployeeInfo(EmployeeEntity employeeEntity) {
        return save(employeeEntity);
    }
}
