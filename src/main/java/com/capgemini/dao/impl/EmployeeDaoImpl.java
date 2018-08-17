package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


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

    @Override
    public EmployeeEntity setOffice(long employee_id, long office_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e where e.id = :employee_id"
                , EmployeeEntity.class);
        queryEmployee.setParameter("id", employee_id);

        TypedQuery<OfficeEntity> queryOffice = entityManager.createQuery(
                "select e from OfficeEntity e where e.id = :office_id"
                , OfficeEntity.class);
        queryEmployee.setParameter("id", office_id);

        try {
            OfficeEntity officeEntity = queryOffice.getSingleResult();
            EmployeeEntity employeeEntity = queryEmployee.getSingleResult();
            employeeEntity.setOffice(officeEntity);
            return entityManager.merge(employeeEntity);
        } catch (NoResultException e) {
            return null;
        }
    }
}
