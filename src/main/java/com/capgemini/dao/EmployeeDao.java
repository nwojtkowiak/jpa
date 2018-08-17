package com.capgemini.dao;

import com.capgemini.domain.EmployeeEntity;

/*
i.	Dodaj samochód,
ii.	Usuń samochód,
iii.Zmień dane samochodu,
iv.	Przypisz do opiekuna,
v.	wyszukaj po typie i marce,
vi.	wyszukaj po opiekunie
*/
public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

    EmployeeEntity add(EmployeeEntity entity);

    void deleteEmployee(long id);

    EmployeeEntity updateEmployeeInfo(EmployeeEntity employeeEntity);

    EmployeeEntity setOffice(long employee_id, long office_id);

}
