package com.capgemini.service;

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


    EmployeeTO findEmployeeById(long employee_id);

    List<EmployeeTO> findAllEmployee();

    EmployeeTO addEmployee(EmployeeTO employee);

    void deleteEmployee(long employee_id);

    EmployeeTO addOfficeToEmployee(Long employee_id, Long office_id);

    EmployeeTO deleteOfficeFromEmployee(Long employee_id, Long office_id);

    List<EmployeeTO> findEmployeeByOfficeId(long office_id);

    List<EmployeeTO> findEmployeeByOfficeIdAndCarId(long office_id, long car_id);

    PositionTO addPosition(PositionTO position);

    List<EmployeeTO> findEmployeeByCriteria(EmployeeSearchCriteriaTO criteria);

}
