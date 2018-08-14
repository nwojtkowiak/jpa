package com.capgemini.service.impl;

import com.capgemini.domain.EmployeeEntity;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.TypeTO;

import java.util.List;
/*
i.	Dodaj samochód,
ii.	Usuń samochód,
iii.Zmień dane samochodu,
iv.	Przypisz do opiekuna,
v.	wyszukaj po typie i marce,
vi.	wyszukaj po opiekunie
*/
public interface CarService {

	List<CarTO> findAllCars();

	List<CarTO> findCarsByKeeper(long employee_id);

	List<CarTO> findCarsByTypeAndMark(String type, String mark);

	CarTO findCarById(long id);

	void addKeeper(CarTO carTO,EmployeeTO employeeTO);

	CarTO addCar(CarTO car);

	CarTO updateCar(CarTO car);

	void deleteCar(long car_id);
}
