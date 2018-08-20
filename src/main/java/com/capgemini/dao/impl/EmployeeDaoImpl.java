package com.capgemini.dao.impl;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.EmployeeSearchCriteriaTO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;


@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao {


    @Override
    public EmployeeEntity add(EmployeeEntity entity) {
        return save(entity);
    }

    @Override
    public EmployeeEntity updateEmployeeInfo(EmployeeEntity entity) {
        return update(entity);
    }

    /**
     * This method finds all emplyees who work in an office with office_id
     *
     * @param office_id - office id
     * @return list of EmployeeEntity or empty list
     */
    @Override
    public List<EmployeeEntity> findAllByOfficeId(long office_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e inner join e.office o where o.id = :office_id"
                , EmployeeEntity.class);

        queryEmployee.setParameter("office_id", office_id);

        try {

            List<EmployeeEntity> employeeEntity = queryEmployee.getResultList();
            return employeeEntity;
        } catch (NoResultException e) {
            return new LinkedList<>();
        }
    }

    /**
     * This method finds all employees who work in an office with office_id and are keepers of car with car_id
     *
     * @param office_id - office id
     * @param car_id    - car id
     * @return list of EmployeeEntity or empty list
     */
    @Override
    public List<EmployeeEntity> findAllByOfficeIdAndCarId(long office_id, long car_id) {
        TypedQuery<EmployeeEntity> queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e " +
                        " inner join e.office o " +
                        " inner join e.cars c " +
                        " where o.id = :office_id " +
                        " and c.id = :car_id"
                , EmployeeEntity.class);

        queryEmployee.setParameter("office_id", office_id);
        queryEmployee.setParameter("car_id", car_id);

        try {

            return queryEmployee.getResultList();

        } catch (NoResultException e) {
            return new LinkedList<>();
        }
    }

    /**
     * This method finds all employees who have information according with searchCriteria
     * This method finds only for filled fields
     *
     * @param searchCriteria transport object with search criteria
     * @return list of EmployeeEntity or empty list
     */
    public List<EmployeeEntity> findAllByEmployeeCriteria(EmployeeSearchCriteriaTO searchCriteria) {
        TypedQuery<EmployeeEntity> queryEmployee;

        StringBuilder builderJoin = new StringBuilder();
        StringBuilder builderWhere = new StringBuilder();
        builderWhere.append(" where ");

        boolean office = false;
        boolean position = false;
        boolean car = false;

        if (searchCriteria.getCarId() != null) {
            builderJoin.append(" inner join e.cars c ");
            builderWhere.append(" c.id = :car_id ");
            car = true;
        }

        if (searchCriteria.getOfficeName() != null) {
            if (car) {
                builderWhere.append(" and e.office.name = :officeName ");
            } else {
                builderWhere.append(" e.office.name = :officeName ");
            }
            office = true;
        }
        if (searchCriteria.getPositionName() != null) {
            if (office) {
                builderWhere.append(" and e.position.name = :positionName ");
            } else {
                builderWhere.append(" e.position.name = :positionName ");
            }
            position = true;
        }

        queryEmployee = entityManager.createQuery(
                "select e from EmployeeEntity e " + builderJoin.toString() + builderWhere.toString()
                , EmployeeEntity.class);

        if (car) {
            queryEmployee.setParameter("car_id", searchCriteria.getCarId());
        }
        if (office) {
            queryEmployee.setParameter("officeName", searchCriteria.getOfficeName());
        }
        if (position) {
            queryEmployee.setParameter("positionName", searchCriteria.getPositionName());
        }


        try {
            return queryEmployee.getResultList();

        } catch (NoResultException e) {
            return new LinkedList<>();
        }

    }
}
