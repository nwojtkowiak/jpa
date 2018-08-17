package com.capgemini.service;


import com.capgemini.types.AddressTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.types.OfficeTO;
import com.capgemini.types.PositionTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

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


    private OfficeTO createOffice(String name, String phoneNumber) {
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().
                withStreet("Polarowa").withBuilding(2).
                withFlat(1).withCity("Poznan").withPostCode("61-852").build();

        OfficeTO officeTO = new OfficeTO.OfficeTOBuilder().
                withAddress(addressOfficeTO).withName(name).
                withPhoneNumber(phoneNumber).build();

        return officeTO;
    }

    private EmployeeTO createEmployee(String firstName, String lastName, Date date) {
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();

        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();

        return new EmployeeTOBuilder().
                withFirstName(firstName).
                withLastName(lastName).
                withBirthDay(date).withAddress(addressTO).withOffice(null).withPosition(positionTO).build();
    }

    @Test
    @Transactional
    public void testShouldReturnIdOfficeAfterAdd() {
        //give
        String name = "Qwerty";
        String phone = "45623951";
        OfficeTO createdOffice = createOffice(name, phone);


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
        OfficeTO createdOffice = createOffice(name, phone);
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
        OfficeTO createdOffice = createOffice(name, phone);
        OfficeTO savedOffice = officeService.addOffice(createdOffice);

        //when
        savedOffice.setName("asdfgh");
        OfficeTO updatedOffice = officeService.updateOffice(savedOffice);

        //then
        assertNotNull(updatedOffice);
        assertEquals("asdfgh",updatedOffice.getName());
        assertEquals(phone,updatedOffice.getPhoneNumber());

    }


}
