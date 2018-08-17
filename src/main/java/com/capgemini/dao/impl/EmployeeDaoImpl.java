package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {


    @Override
    public EmployeeEntity add(EmployeeEntity entity) {
        return save(entity);
    }

    @Override
    public void deleteEmployee(Long id) {  delete(id); }

    @Override
    public EmployeeEntity updateEmployeeInfo(EmployeeEntity employeeEntity) {
        return save(employeeEntity);
    }

    @Override
    public EmployeeEntity setOffice(Long employee_id, Long office_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e where e.id = :employee_id"
                , EmployeeEntity.class);
        queryEmployee.setParameter("employee_id", employee_id);

        TypedQuery<OfficeEntity> queryOffice = entityManager.createQuery(
                "select e from OfficeEntity e where e.id = :office_id"
                , OfficeEntity.class);
        queryOffice.setParameter("office_id", office_id);

        try {
            OfficeEntity officeEntity = queryOffice.getSingleResult();
            EmployeeEntity employeeEntity = queryEmployee.getSingleResult();
            employeeEntity.setOffice(officeEntity);
            return entityManager.merge(employeeEntity);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public EmployeeEntity removeOffice(Long employee_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e where e.id = :employee_id"
                , EmployeeEntity.class);
        queryEmployee.setParameter("employee_id", employee_id);

        try {

            EmployeeEntity employeeEntity = queryEmployee.getSingleResult();
            employeeEntity.setOffice(null);
            return entityManager.merge(employeeEntity);
        } catch (NoResultException e) {
            return null;
        }
    }


    @Override
    public List<EmployeeEntity> findAllByOfficeId(Long office_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e inner join e.office o where o.id = :office_id"
                , EmployeeEntity.class);
        queryEmployee.setParameter("office_id", office_id);

        try {

            List<EmployeeEntity> employeeEntity = queryEmployee.getResultList();
            return employeeEntity;

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<EmployeeEntity> findAllByOfficeIdAndCarId(long office_id, long car_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e inner join e.office o " +
                        " inner join e.cars c where o.id = :office_id " +
                        " and c.id = :car_id"
                , EmployeeEntity.class);
        queryEmployee.setParameter("office_id", office_id);
        queryEmployee.setParameter("car_id", car_id);

        try {

            List<EmployeeEntity> employeeEntity = queryEmployee.getResultList();
            return employeeEntity;

        } catch (NoResultException e) {
            return null;
        }
    }
}
