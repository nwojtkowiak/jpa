package com.capgemini.service;

import com.capgemini.types.AddressTO;
import com.capgemini.types.EmployeeSearchCriteriaTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;

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

    EmployeeTO addOfficeToEmployee(Long employee_id, Long office_id);

    List<EmployeeTO> findEmployeeByOfficeId(long office_id);

    List<EmployeeTO> findEmployeeByOfficeIdAndCarId(long office_id, long car_id);

    EmployeeTO delOfficeFromEmployee(Long employee_id, Long office_id);

    PositionTO addPosition(PositionTO position);

    List<EmployeeTO> findEmployeeByCriteria(EmployeeSearchCriteriaTO criteria);

}
