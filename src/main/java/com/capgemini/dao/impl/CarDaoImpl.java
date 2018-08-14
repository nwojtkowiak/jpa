package com.capgemini.dao.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.Year;
import java.util.List;


@Repository
public class CarDaoImpl extends AbstractDao<CarEntity, Long> implements CarDao {

    /*@Override
    public List<BookEntity> findBookByTitle(String title) {
        TypedQuery<BookEntity> query = entityManager.createQuery(
                "select book from BookEntity book where upper(book.title) like concat(upper(:title), '%')", BookEntity.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

	@Override
	public List<BookEntity> findBooksByAuthor(Long authorId) {
		TypedQuery<BookEntity> query = entityManager.createNamedQuery("books.findBooksByAuthor", BookEntity.class);
		query.setParameter("authorId", authorId);
		return query.getResultList();
	}*/

    @Override
    public CarEntity findCarById(long id) {
        return null;
    }

    @Override
    public List<CarEntity> findCarsByMark(String mark) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByMark", CarEntity.class);
        query.setParameter("mark", mark);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsByModel(String model) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByModel", CarEntity.class);
        query.setParameter("model", model);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsByProdYear(Year prodYear) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByProdYear", CarEntity.class);
        query.setParameter("prodYear", prodYear);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsByCourse(double course) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByCourse", CarEntity.class);
        query.setParameter("course", course);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsByCapacity(double capacity) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByCapacity", CarEntity.class);
        query.setParameter("capacity", capacity);
        return query.getResultList();
    }

    @Override
    public List<CarEntity> findCarsByPower(int power) {
        TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByPower", CarEntity.class);
        query.setParameter("power", power);
        return query.getResultList();
    }


    @Override
    public List<CarEntity> findCarsByTypeAndMark(String type, String mark) {
        //TypedQuery<CarEntity> query = entityManager.createNamedQuery("car.findCarsByTypeAndMark", CarEntity.class);
        Query query = entityManager.createQuery(
                "select car from CarEntity car join TypeEntity type on" +
                        "type.id = car.type_id where type.name = :type and car.mark = :mark ");
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

    }
}
