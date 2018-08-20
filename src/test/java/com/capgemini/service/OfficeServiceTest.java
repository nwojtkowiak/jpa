package com.capgemini.service;


import com.capgemini.types.*;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import static com.capgemini.service.HelpMethods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeServiceTest {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CarService carService;

    @Autowired
    private AddressService addressService;




    @Test
    @Transactional
    public void testShouldReturnIdOfficeAfterAdd() {
        //give
        String name = "Qwerty";
        String phone = "45623951";

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());

        //when
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        //then
        assertNotNull(savedOffice);
        assertNotNull(savedOffice.getId());
        assertEquals(name, savedOffice.getName());
        assertEquals(phone, savedOffice.getPhoneNumber());
    }

    @Test
    @Transactional
    public void testShouldBeNullAfterDelOffice() {
        //give
        String name = "Qwerty";
        String phone = "45623951";

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        //when
        officeService.deleteOffice(savedOffice.getId());

        //then
        OfficeTO findOffice = officeService.findOfficeById(savedOffice.getId());
        assertNull(findOffice);

    }

    @Test
    @Transactional
    public void testShouldReturnNewInfoAfterUpdate() {
        //give
        String name = "Qwerty";
        String phone = "45623951";

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        //when
        savedOffice.setName("asdfgh");
        OfficeTO updatedOffice = officeService.updateOffice(savedOffice);

        //then
        assertNotNull(updatedOffice);
        assertEquals("asdfgh",updatedOffice.getName());
        assertEquals(phone,updatedOffice.getPhoneNumber());

    }

    @Test
    @Transactional
    public void testShouldSetEmployeeOfficeId() {
        //give
        String name = "Qwerty";
        String phone = "45623951";

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO = employeeService.addPosition(positionTO);

        EmployeeTO employeeTO = createEmployee("Ania","Testowa",new Date(19200606),
                addressTO.getId(), savedOffice.getId(), positionTO.getId());
        EmployeeTO savedEmployee = employeeService.addEmployee(employeeTO);

        //when
        officeService.addEmployeeToOffice(savedEmployee,savedOffice);
        EmployeeTO foundEmployee = employeeService.findEmployeeById(savedEmployee.getId());

        //then
        assertNotNull(foundEmployee);
        assertNotNull(foundEmployee.getOffice());
        assertEquals(savedOffice.getId(),foundEmployee.getOffice());

    }

    @Test
    @Transactional
    public void testShouldSetEmployeeOfficeIdForNull() {
        //give
        String name = "Qwerty";
        String phone = "45623951";

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO = employeeService.addPosition(positionTO);

        EmployeeTO employeeTO = createEmployee("Ania","Testowa",new Date(19200606),addressTO.getId(), savedOffice.getId(), positionTO.getId() );
        EmployeeTO savedEmployee = employeeService.addEmployee(employeeTO);
        officeService.addEmployeeToOffice(savedEmployee,savedOffice);

        //when
        officeService.delEmployeeFromOffice(savedEmployee.getId(), savedOffice.getId());
        EmployeeTO foundEmployee = employeeService.findEmployeeById(savedEmployee.getId());

        //then
        assertNotNull(foundEmployee);
        assertNull(foundEmployee.getOffice());

    }

    @Test
    @Transactional
    public void testShouldReturnListEmployeeWithSize2() {
        //give
        String name = "Qwerty";
        String phone = "45623951";

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO = employeeService.addPosition(positionTO);

        EmployeeTO employeeTO = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),savedOffice.getId(), positionTO.getId());
        EmployeeTO savedEmployee = employeeService.addEmployee(employeeTO);

        EmployeeTO employeeTO2 = createEmployee("Roman","Testowy",new Date(19200606), addressTO.getId(),savedOffice.getId(), positionTO.getId());
        EmployeeTO savedEmployee2 = employeeService.addEmployee(employeeTO2);

        officeService.addEmployeeToOffice(savedEmployee,savedOffice);
        officeService.addEmployeeToOffice(savedEmployee2,savedOffice);

        //when
        List<EmployeeTO> employees = officeService.findEmployeesByOffice(savedOffice.getId());

        //then
        assertNotNull(employees);
        assertEquals(2,employees.size());

    }

    @Test
    @Transactional
    public void testShouldReturnListEmployeeWithSize1ByOfficeAndCar() {
        //give

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        CarTO createdCar = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO = employeeService.addPosition(positionTO);

        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOfficeTO = addressService.addAddress(addressOfficeTO);

        String name = "Qwerty";
        String phone = "45623951";

        OfficeTO createdOffice = createOffice(name, phone, addressOfficeTO.getId());
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        EmployeeTO employeeTO = createEmployee("Ania","Testowa",new Date(19200606), addressTO.getId(),null, positionTO.getId());
        EmployeeTO savedEmployee = employeeService.addEmployee(employeeTO);

        carService.addKeeper(savedCar, savedEmployee);
        officeService.addEmployeeToOffice(savedEmployee,savedOffice);

        //when
        List<EmployeeTO> employees = officeService.findEmployeeByOfficeAndCar(savedOffice.getId(), savedCar.getId());

        //then
        assertNotNull(employees);
        assertEquals(1,employees.size());

    }


}
