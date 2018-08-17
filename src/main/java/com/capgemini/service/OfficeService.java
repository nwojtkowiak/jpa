package com.capgemini.service;

import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;

import java.util.List;

/*
i.	Dodaj placówkę,
ii.	Usuń placówkę,
iii.Zmień dane placówki,
iv.	dodaj pracownika do placówki,
v.	usuń pracownika z placówki,
vi.	wyszukaj aktualnych pracowników placówki,
vii. wyszukaj wszystkich pracowników placówki, którzy opiekują się podanym samochodem)

*/
public interface OfficeService {

    OfficeTO addOffice(OfficeTO office);

    void deleteOffice(long id);

    OfficeTO updateOffice(OfficeTO office);

    void addEmployeeToOffice(EmployeeTO employee, OfficeTO office);

    void delEmployeeFromOffice(long employee_id, long office_id);

    List<EmployeeTO> findEmployeesByOffice(long office_id);

    List<EmployeeTO> findEmployeeByOfficeAndCar(long office_id, long car_id);

    OfficeTO findOfficeById(long office_id);
}
