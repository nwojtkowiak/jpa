package com.capgemini.service;


import com.capgemini.types.*;
import com.capgemini.types.CarTO.CarTOBuilder;
import com.capgemini.types.ColorTO.ColorToBuilder;
import com.capgemini.types.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.types.TypeTO.TypeToBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Year;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private EmployeeService employeeService;
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

    private TypeTO createType(String name) {
        return new TypeToBuilder().withName(name).build();
    }

    private ColorTO createColor(String name) {
        return new ColorToBuilder().withName(name).build();
    }

    private CarTO createCar(String mark, String model, int prodYear, double capacity,
                            int power, long course, ColorTO colorTo, TypeTO typeTo) {


        CarTO car = new CarTOBuilder().withMark(mark)
                .withModel(model)
                .withCapacity(capacity)
                .withColor(colorTo)
                .withCourse(course)
                .withPower(power)
                .withProdYear(Year.of(prodYear))
                .withType(typeTo).build();

        return car;
    }

    private EmployeeTO createEmployee(String firstName, String lastName, Date date) {
        AddressTO addressTO = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        AddressTO addressOfficeTO = new AddressTO.AddressTOBuilder().withStreet("Polarowa").withBuilding(2).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        OfficeTO officeTO = new OfficeTO.OfficeTOBuilder().withAddress(addressOfficeTO).withName("Polarowa").withPhoneNumber("7894561212").build();
        PositionTO positionTO = new PositionTO.PositionToBuilder().withName("dealer").build();

        return new EmployeeTOBuilder().
                withFirstName(firstName).
                withLastName(lastName).
                withBirthDay(date).withAddress(addressTO).withOffice(officeTO).withPosition(positionTO).build();
    }

    @Test
    @Transactional
    public void testShouldFindCarByIdAfterAdd() {
        // given
        ColorTO colorSilver = createColor("silver");
        carService.addColor(colorSilver);
        ColorTO colorBlack = createColor("black");
        carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        carService.addType(typeHatchback);
        TypeTO typeCombi = createType("combi");
        carService.addType(typeCombi);


        CarTO createdCar = createCar("Peugeot", "206", 2002, 1.4d, 60,
                234500, colorSilver, typeHatchback);
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
        carService.addColor(colorSilver);

        TypeTO typeHatchback = createType("hatchback");
        carService.addType(typeHatchback);

        CarTO createdCar = createCar("Peugeot", "206", 2002, 1.4d, 60,
                234500, colorSilver, typeHatchback);
        CarTO savedCar = carService.addCar(createdCar);

        // when
        carService.deleteCar(savedCar.getId());
        CarTO foundCar = carService.findCarById(savedCar.getId());

        // then
        assertNull(foundCar);

    }

    @Test
    @Transactional
    public void testShouldDeleteCarsAfterDeleteColor() {
        ColorTO colorSilver = createColor("silver");
        ColorTO colorBlack = createColor("black");
        TypeTO typeHatchback = createType("hatchback");
        TypeTO typeCombi = createType("combi");

        CarTO createdCar = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack, typeHatchback);
        CarTO savedCar = carService.addCar(createdCar);


        carService.deleteCar(savedCar.getId());
        List<ColorTO> colors = carService.findAllColors();
        List<CarTO> cars = carService.findAllCars();
        assertEquals(2, colors.size());
        assertEquals(0, cars.size());

        createdCar = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack, typeCombi);
        savedCar = carService.addCar(createdCar);

        carService.deleteColor(colors.get(0).getId());
        carService.deleteColor(colors.get(1).getId());

        assertEquals(1, colors.size());
        assertEquals(0, cars.size());
    }

    @Test
    @Transactional
    public void testShouldFindCarByTypeAndMark() {
        // given
        ColorTO colorBlack = createColor("black");
        TypeTO typeHatchback = createType("hatchback");
        TypeTO typeCombi = createType("combi");
        String mark = "Opel";
        String type = "hatchback";

        CarTO createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack, typeHatchback);
        CarTO savedCar = carService.addCar(createdCar);

        createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack, typeHatchback);
        savedCar = carService.addCar(createdCar);

        createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack, typeCombi);
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
        TypeTO typeHatchback = createType("hatchback");
        TypeTO typeCombi = createType("combi");
        String mark = "Opel";
        String type = "hatchback";

        colorBlack = carService.addColor(colorBlack);
        typeHatchback = carService.addType(typeHatchback);
        typeCombi = carService.addType(typeCombi);

        CarTO createdCar = createCar(mark, "Corsa", 2002, 1.4d, 60,
                234500, colorBlack, typeHatchback);
        CarTO savedCar = carService.addCar(createdCar);

        // when
        CarTO foundCar = carService.findCarById(savedCar.getId());
        foundCar.setType(typeCombi);
        CarTO updatedCar = carService.updateCar(foundCar);

        // then
        assertNotNull(foundCar);
        assertEquals(typeCombi, updatedCar.getType());

    }

    @Test
    @Transactional
    public void testShouldReturnCarByKeeperAfterAddKeeper() {

        // given
        ColorTO colorBlack = createColor("black");
        TypeTO typeHatchback = createType("hatchback");


        CarTO createdCar = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack, typeHatchback);
        CarTO savedCar = carService.addCar(createdCar);

        EmployeeTO employeeTO = createEmployee("F", "L", new Date(19910102));
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
