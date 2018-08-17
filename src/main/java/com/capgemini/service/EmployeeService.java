package com.capgemini.service;

import com.capgemini.types.*;

import java.util.List;

/*
i.	Dodaj samochód,
ii.	Usuń samochód,
iii.Zmień dane samochodu,
iv.	Przypisz do opiekuna,
v.	wyszukaj po typie i marce,
vi.	wyszukaj po opiekunie
*/
public interface EmployeeService {

	List<EmployeeTO> findAllEmployees();

	EmployeeTO findEmployeeById(long id);

	EmployeeTO addEmployee(EmployeeTO employee);

	void deleteEmployee(long employee_id);

	//AddressTO addAddress(AddressTO address);


}
