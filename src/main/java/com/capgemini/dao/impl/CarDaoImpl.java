package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public List<CarEntity> findCarsByTypeAndMark(String type, String mark) {

        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car inner join car.type t "
                        + "where t.name = :type and car.mark = :mark", CarEntity.class);

        query.setParameter("type", type);
        query.setParameter("mark", mark);
        return query.getResultList();
    }

    /**
     * This method finds list of car with keeper with employee_id
     * @param employee_id - employee id whose is keeper this car
     * @return list of CarEntity or empty list
     */
    @Override
    public List<CarEntity> findCarsByKeeper(long employee_id) {
        Query query = entityManager.createQuery(
                "select e.cars from EmployeeEntity e where id = :employee_id ");
        query.setParameter("employee_id", employee_id);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new LinkedList<>();
        }
    }

    @Override
    public CarEntity add(CarEntity entity) {
        return save(entity);
    }

    @Override
    public void deleteCar(long id) {
        delete(id);
    }

}
