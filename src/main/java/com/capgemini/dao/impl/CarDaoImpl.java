package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    @Override
    public List<CarEntity> findCarsByTypeAndMark(String type, String mark) {

        TypedQuery<CarEntity> query = entityManager.createQuery(
               "select car from CarEntity car inner join car.type t "+"where t.name = :type and car.mark = :mark",CarEntity.class);

        query.setParameter("type", type);
        query.setParameter("mark", mark);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsByKeeper(long employee_id) {
        TypedQuery<CarEntity> query = entityManager.createQuery(
                "select car from CarEntity car where :employee_id member of car.keepers ", CarEntity.class);
        query.setParameter("employee_id", employee_id);
        return query.getResultList();
    }

    @Override
    public CarEntity addCar(CarEntity carEntity) {
        return save(carEntity);
    }

    @Override
    public void deleteCar(long id) {
        delete(id);
    }

    @Override
    public CarEntity updateCarInfo(CarEntity carEntity) {
        return update(carEntity);
    }

    //TODO
    @Override
    public void addKeeper(CarEntity carEntity, EmployeeEntity employeeEntity) {
        TypedQuery<EmployeeEntity> query = entityManager.createQuery (
                "select e from EmployeeEntity e where e.id = :id", EmployeeEntity.class);
        query.setParameter("id", employeeEntity.getId());
        EmployeeEntity foundEmployee = query.getSingleResult();
        foundEmployee.getCars().add(carEntity);

        entityManager.merge(foundEmployee);
    }
}
