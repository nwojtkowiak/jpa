package com.capgemini.dao;

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

    EmployeeEntity add(EmployeeEntity entity);

    void deleteEmployee(Long id);

    EmployeeEntity updateEmployeeInfo(EmployeeEntity employeeEntity);

    EmployeeEntity setOffice(Long employee_id, Long office_id);

    EmployeeEntity removeOffice(Long employee_id);

    List<EmployeeEntity> findAllByOfficeId(Long office_id);

    List<EmployeeEntity> findAllByOfficeIdAndCarId(long office_id, long car_id);


}
