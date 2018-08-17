package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.TypeEntity;

import java.time.Year;
import java.util.List;
/*
i.	Dodaj samochód,
ii.	Usuń samochód,
iii.Zmień dane samochodu,
iv.	Przypisz do opiekuna,
v.	wyszukaj po typie i marce,
vi.	wyszukaj po opiekunie
*/
public interface CarDao extends Dao<CarEntity, Long> {

     List<CarEntity> findCarsByTypeAndMark(String type, String mark);

    List<CarEntity> findCarsByKeeper(long employee_id);

    CarEntity add(CarEntity entity);

	void deleteCar(long id);

    CarEntity updateCarInfo(CarEntity carEntity);

	void addKeeper(CarEntity carEntity, EmployeeEntity employeeEntity);
}
