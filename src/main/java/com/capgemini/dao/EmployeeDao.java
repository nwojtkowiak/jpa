package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.EmployeeEntity;

import java.util.List;

/*
i.	Dodaj samochód,
ii.	Usuń samochód,
iii.Zmień dane samochodu,
iv.	Przypisz do opiekuna,
v.	wyszukaj po typie i marce,
vi.	wyszukaj po opiekunie
*/
public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

    EmployeeEntity addEmployee(EmployeeEntity employeeEntity);

	void deleteEmployee(long id);

    EmployeeEntity updateEmployeeInfo(EmployeeEntity employeeEntity);

}
