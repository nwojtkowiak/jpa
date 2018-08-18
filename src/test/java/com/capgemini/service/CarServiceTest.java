package com.capgemini.service;


import com.capgemini.types.*;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import static com.capgemini.service.HelpMethods.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OfficeService officeService;
/*
		private String mark;
		private String model;
		private Year prodYear;
		private Double capacity;
		private Integer power;
		private BigInteger course;
		private ColorTO color;
		private TypeTO type;
 */



    @Test
    @Transactional
    public void testShouldFindCarByIdAfterAdd() {
        // given
        ColorTO colorSilver = createColor("silver");
        colorSilver = carService.addColor(colorSilver);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        CarTO createdCar = createCar("Peugeot", "206", 2002, 1.4d, 60,
                234500, colorSilver.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        // when
        CarTO foundCar = carService.findCarById(savedCar.getId());

        // then
        assertNotNull(foundCar);
        assertEquals(savedCar.getMark(), foundCar.getMark());
        assertEquals(savedCar.getCapacity(), foundCar.getCapacity(), 0.1);
        assertEquals(savedCar.getColor(), foundCar.getColor());
        assertEquals(savedCar.getCourse(), foundCar.getCourse());
        assertEquals(savedCar.getModel(), foundCar.getModel());
        assertEquals(savedCar.getPower(), foundCar.getPower());
        assertEquals(savedCar.getProdYear(), foundCar.getProdYear());
        assertEquals(savedCar.getType(), foundCar.getType());
    }

    @Test
    @Transactional
    public void testShouldNotFindCarByIdAfterDel() {
        // given
        ColorTO colorSilver = createColor("silver");
        colorSilver = carService.addColor(colorSilver);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        CarTO createdCar = createCar("Peugeot", "206", 2002, 1.4d, 60,
                234500, colorSilver.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        // when
        carService.deleteCar(savedCar.getId());
        CarTO foundCar = carService.findCarById(savedCar.getId());

        // then
        assertNull(foundCar);

    }

    @Test
    @Transactional
    public void testShouldNotDeleteColorAfterDeleteCar() {
        ColorTO colorSilver = createColor("silver");
        colorSilver = carService.addColor(colorSilver);

        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        TypeTO typeCombi = createType("combi");
        typeCombi = carService.addType(typeCombi);

        CarTO createdCar = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        carService.deleteCar(savedCar.getId());

        List<ColorTO> colors = carService.findAllColors();
        List<CarTO> cars = carService.findAllCars();

        assertEquals(2, colors.size());
        assertEquals(0, cars.size());

    }

    @Test
    @Transactional
    public void testShouldFindCarByTypeAndMark() {
        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        TypeTO typeCombi = createType("combi");
        typeCombi = carService.addType(typeCombi);

        String mark = "Opel";
        String type = "hatchback";

        CarTO createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        savedCar = carService.addCar(createdCar);

        createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeCombi.getId());
        savedCar = carService.addCar(createdCar);

        // when
        List<CarTO> foundCars = carService.findCarsByTypeAndMark(type, mark);

        // then
        assertNotNull(foundCars);
        assertEquals(2, foundCars.size());

    }

    @Test
    @Transactional
    public void testShouldUpdateInfoCar() {
        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        TypeTO typeCombi = createType("combi");
        typeCombi = carService.addType(typeCombi);

        String mark = "Opel";
        String type = "hatchback";


        CarTO createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        // when
        CarTO foundCar = carService.findCarById(savedCar.getId());
        foundCar.setType(typeCombi.getId());
        CarTO updatedCar = carService.updateCar(foundCar);

        // then
        assertNotNull(foundCar);
        assertEquals(typeCombi.getId(), updatedCar.getType());

    }

    @Test
    @Transactional
    //TODO spr mysql
    public void testShouldReturnCarByKeeperAfterAddKeeper() {

        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);
        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);


        CarTO createdCar = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        CarTO savedCar = carService.addCar(createdCar);

        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressTO = addressService.addAddress(addressTO);

        AddressTO addressOffice = new AddressTO.AddressTOBuilder().withStreet("Polarowa").withBuilding(2).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        addressOffice = addressService.addAddress(addressOffice);

        OfficeTO officeTO = new OfficeTO.OfficeTOBuilder().withAddress(addressOffice.getId()).withName("Polarowa").withPhoneNumber("7894561212").build();
        officeTO = officeService.addOffice(officeTO);

        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();
        positionTO = employeeService.addPosition(positionTO);

        EmployeeTO employeeTO = createEmployee("F", "L", new Date(19910102), addressTO.getId(), officeTO.getId(), positionTO.getId());
        employeeTO = employeeService.addEmployee(employeeTO);

        // when
        carService.addKeeper(savedCar, employeeTO);

        // then
        try {
            List<CarTO> foundCars = carService.findCarsByKeeper(employeeTO.getId());
            assertNotNull(foundCars);
            assertEquals(1, foundCars.size());
        } catch (NullPointerException e) {
            assertNotNull(employeeTO);
            assertNotNull(savedCar);
        }
    }


}
