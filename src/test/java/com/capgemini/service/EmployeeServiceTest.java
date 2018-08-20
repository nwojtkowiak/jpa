package com.capgemini.service;


import com.capgemini.types.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.types.EmployeeSearchCriteriaTO.EmployeeSearchCriteriaBuilder;

import java.sql.Date;
import java.util.List;

import static com.capgemini.service.HelpMethods.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CarService carService;


    @Test
    @Transactional
    public void testShouldReturnListEmployeeByOfficeAndCarAndPosition() {
        //give

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO createdCar1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar1 = carService.addCar(createdCar1);

        CarTO createdCar2 = createCar("Nissan", "Almera", 2018, 1.4d, 60,
                1000, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar2 = carService.addCar(createdCar2);

        //adres
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        //stanowiska
        PositionTO positionTO1 = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO1 = employeeService.addPosition(positionTO1);

        PositionTO positionTO2 = new PositionTO.PositionToBuilder().withName("manager").build();
        positionTO2 = employeeService.addPosition(positionTO2);

        //biura
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice1 = createOffice("Qwerty", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice1 = officeService.addOffice(createdOffice1);

        OfficeTO createdOffice2 = createOffice("Asdf", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice2 = officeService.addOffice(createdOffice2);

        //pracownicy
        EmployeeTO employeeTO1 = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee1 = employeeService.addEmployee(employeeTO1);

        EmployeeTO employeeTO2 = createEmployee("Roman","Testowy",new Date(19490609), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employeeTO2);

        EmployeeTO employeeTO3 = createEmployee("Ania","Kowalska",new Date(19880806), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employeeTO3);

        EmployeeTO employeeTO4 = createEmployee("Asia","Nowak",new Date(19880806), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employeeTO4);

        carService.addKeeper(savedCar1, savedEmployee1);
        officeService.addEmployeeToOffice(savedEmployee1,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee2);
        officeService.addEmployeeToOffice(savedEmployee2,savedOffice2);

        carService.addKeeper(savedCar2, savedEmployee3);
        officeService.addEmployeeToOffice(savedEmployee3,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee4);
        officeService.addEmployeeToOffice(savedEmployee4,savedOffice1);

        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaBuilder().
                withCar(savedCar1.getId()).
                withOffice(savedOffice1.getName())
                .withPosition(positionTO1.getName()).build();

        //when
        List<EmployeeTO> employees = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertNotNull(employees);
        assertEquals(2,employees.size());
        assertEquals("Testowa",employees.get(0).getLastName());
        assertEquals("Nowak",employees.get(1).getLastName());
    }

    @Test
    @Transactional
    public void testShouldReturnListEmployeeByOfficeAndCar() {
        //give

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO createdCar1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar1 = carService.addCar(createdCar1);

        CarTO createdCar2 = createCar("Nissan", "Almera", 2018, 1.4d, 60,
                1000, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar2 = carService.addCar(createdCar2);

        //adres
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        //stanowiska
        PositionTO positionTO1 = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO1 = employeeService.addPosition(positionTO1);

        PositionTO positionTO2 = new PositionTO.PositionToBuilder().withName("manager").build();
        positionTO2 = employeeService.addPosition(positionTO2);

        //biura
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice1 = createOffice("Qwerty", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice1 = officeService.addOffice(createdOffice1);

        OfficeTO createdOffice2 = createOffice("Asdf", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice2 = officeService.addOffice(createdOffice2);

        //pracownicy
        EmployeeTO employeeTO1 = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee1 = employeeService.addEmployee(employeeTO1);

        EmployeeTO employeeTO2 = createEmployee("Roman","Testowy",new Date(19490609), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employeeTO2);

        EmployeeTO employeeTO3 = createEmployee("Ania","Kowalska",new Date(19880806), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employeeTO3);

        EmployeeTO employeeTO4 = createEmployee("Asia","Nowak",new Date(19880806), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employeeTO4);

        carService.addKeeper(savedCar1, savedEmployee1);
        officeService.addEmployeeToOffice(savedEmployee1,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee2);
        officeService.addEmployeeToOffice(savedEmployee2,savedOffice2);

        carService.addKeeper(savedCar2, savedEmployee3);
        officeService.addEmployeeToOffice(savedEmployee3,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee4);
        officeService.addEmployeeToOffice(savedEmployee4,savedOffice1);

        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaBuilder().
               withCar(savedCar1.getId()).
                withOffice(savedOffice1.getName()).build();

        //when
        List<EmployeeTO> employees = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertNotNull(employees);
        assertEquals(2,employees.size());
        assertEquals("Testowa",employees.get(0).getLastName());
        assertEquals("Nowak",employees.get(1).getLastName());
    }

    @Test
    @Transactional
    public void testShouldReturnListEmployeeByOffice() {
        //give

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO createdCar1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar1 = carService.addCar(createdCar1);

        CarTO createdCar2 = createCar("Nissan", "Almera", 2018, 1.4d, 60,
                1000, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar2 = carService.addCar(createdCar2);

        //adres
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        //stanowiska
        PositionTO positionTO1 = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO1 = employeeService.addPosition(positionTO1);

        PositionTO positionTO2 = new PositionTO.PositionToBuilder().withName("manager").build();
        positionTO2 = employeeService.addPosition(positionTO2);

        //biura
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice1 = createOffice("Qwerty", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice1 = officeService.addOffice(createdOffice1);

        OfficeTO createdOffice2 = createOffice("Asdf", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice2 = officeService.addOffice(createdOffice2);

        //pracownicy
        EmployeeTO employeeTO1 = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee1 = employeeService.addEmployee(employeeTO1);

        EmployeeTO employeeTO2 = createEmployee("Roman","Testowy",new Date(19490609), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employeeTO2);

        EmployeeTO employeeTO3 = createEmployee("Ania","Kowalska",new Date(19880806), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employeeTO3);

        EmployeeTO employeeTO4 = createEmployee("Asia","Nowak",new Date(19880806), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employeeTO4);

        carService.addKeeper(savedCar1, savedEmployee1);
        officeService.addEmployeeToOffice(savedEmployee1,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee2);
        officeService.addEmployeeToOffice(savedEmployee2,savedOffice1);

        carService.addKeeper(savedCar2, savedEmployee3);
        officeService.addEmployeeToOffice(savedEmployee3,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee4);
        officeService.addEmployeeToOffice(savedEmployee4,savedOffice1);

        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaBuilder().
                withOffice(savedOffice1.getName()).build();

        //when
        List<EmployeeTO> employees = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertNotNull(employees);
        assertEquals(4,employees.size());
        assertEquals("Testowa",employees.get(0).getLastName());
        assertEquals("Testowy",employees.get(1).getLastName());
        assertEquals("Kowalska",employees.get(2).getLastName());
        assertEquals("Nowak",employees.get(3).getLastName());
    }

    @Test
    @Transactional
    public void testShouldReturnListEmployeeByCar() {
        //give

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO createdCar1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar1 = carService.addCar(createdCar1);

        CarTO createdCar2 = createCar("Nissan", "Almera", 2018, 1.4d, 60,
                1000, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar2 = carService.addCar(createdCar2);

        //adres
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        //stanowiska
        PositionTO positionTO1 = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO1 = employeeService.addPosition(positionTO1);

        PositionTO positionTO2 = new PositionTO.PositionToBuilder().withName("manager").build();
        positionTO2 = employeeService.addPosition(positionTO2);

        //biura
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice1 = createOffice("Qwerty", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice1 = officeService.addOffice(createdOffice1);

        OfficeTO createdOffice2 = createOffice("Asdf", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice2 = officeService.addOffice(createdOffice2);

        //pracownicy
        EmployeeTO employeeTO1 = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee1 = employeeService.addEmployee(employeeTO1);

        EmployeeTO employeeTO2 = createEmployee("Roman","Testowy",new Date(19490609), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employeeTO2);

        EmployeeTO employeeTO3 = createEmployee("Ania","Kowalska",new Date(19880806), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employeeTO3);

        EmployeeTO employeeTO4 = createEmployee("Asia","Nowak",new Date(19880806), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employeeTO4);

        carService.addKeeper(savedCar1, savedEmployee1);
        officeService.addEmployeeToOffice(savedEmployee1,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee2);
        officeService.addEmployeeToOffice(savedEmployee2,savedOffice1);

        carService.addKeeper(savedCar2, savedEmployee3);
        officeService.addEmployeeToOffice(savedEmployee3,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee4);
        officeService.addEmployeeToOffice(savedEmployee4,savedOffice1);

        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaBuilder().
                withCar(savedCar2.getId()).build();

        //when
        List<EmployeeTO> employees = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertNotNull(employees);
        assertEquals(1,employees.size());
        assertEquals("Kowalska",employees.get(0).getLastName());
    }

    @Test
    @Transactional
    public void testShouldReturnListEmployeeByPosition() {
        //give

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO createdCar1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar1 = carService.addCar(createdCar1);

        CarTO createdCar2 = createCar("Nissan", "Almera", 2018, 1.4d, 60,
                1000, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar2 = carService.addCar(createdCar2);

        //adres
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        //stanowiska
        PositionTO positionTO1 = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO1 = employeeService.addPosition(positionTO1);

        PositionTO positionTO2 = new PositionTO.PositionToBuilder().withName("manager").build();
        positionTO2 = employeeService.addPosition(positionTO2);

        //biura
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice1 = createOffice("Qwerty", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice1 = officeService.addOffice(createdOffice1);

        OfficeTO createdOffice2 = createOffice("Asdf", "45623951", addressOfficeTO.getId());
        OfficeTO savedOffice2 = officeService.addOffice(createdOffice2);

        //pracownicy
        EmployeeTO employeeTO1 = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee1 = employeeService.addEmployee(employeeTO1);

        EmployeeTO employeeTO2 = createEmployee("Roman","Testowy",new Date(19490609), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employeeTO2);

        EmployeeTO employeeTO3 = createEmployee("Ania","Kowalska",new Date(19880806), addressTO.getId(),null, positionTO2.getId());
        EmployeeTO savedEmployee3 = employeeService.addEmployee(employeeTO3);

        EmployeeTO employeeTO4 = createEmployee("Asia","Nowak",new Date(19880806), addressTO.getId(),null, positionTO1.getId());
        EmployeeTO savedEmployee4 = employeeService.addEmployee(employeeTO4);

        carService.addKeeper(savedCar1, savedEmployee1);
        officeService.addEmployeeToOffice(savedEmployee1,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee2);
        officeService.addEmployeeToOffice(savedEmployee2,savedOffice1);

        carService.addKeeper(savedCar2, savedEmployee3);
        officeService.addEmployeeToOffice(savedEmployee3,savedOffice1);

        carService.addKeeper(savedCar1, savedEmployee4);
        officeService.addEmployeeToOffice(savedEmployee4,savedOffice1);

        EmployeeSearchCriteriaTO criteria = new EmployeeSearchCriteriaBuilder().
                withPosition(positionTO2.getName()).build();

        //when
        List<EmployeeTO> employees = employeeService.findEmployeeByCriteria(criteria);

        //then
        assertNotNull(employees);
        assertEquals(2,employees.size());
        assertEquals("Testowy",employees.get(0).getLastName());
        assertEquals("Kowalska",employees.get(1).getLastName());
    }


}
