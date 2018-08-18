package com.capgemini.service;

import com.capgemini.types.*;

import java.sql.Date;
import java.time.Year;

public class HelpMethods {

    public static TypeTO createType(String name) {
        return new TypeTO.TypeToBuilder().withName(name).build();
    }

    public static ColorTO createColor(String name) {
        return new ColorTO.ColorToBuilder().withName(name).build();
    }

    public static CarTO createCar(String mark, String model, int prodYear, double capacity,
                            int power, long course, Long color, Long type) {


        CarTO car = new CarTO.CarTOBuilder().withMark(mark)
                .withModel(model)
                .withCapacity(capacity)
                .withColor(color)
                .withCourse(course)
                .withPower(power)
                .withProdYear(Year.of(prodYear))
                .withType(type).build();

        return car;
    }

    public static EmployeeTO createEmployee(String firstName, String lastName, Date date, Long address, Long office, Long position) {

        return new EmployeeTO.EmployeeTOBuilder().
                withFirstName(firstName).
                withLastName(lastName).
                withBirthDay(date).withAddress(address).withOffice(office).withPosition(position).build();
    }

    public static OfficeTO createOffice(String name, String phoneNumber, Long address) {

        OfficeTO officeTO = new OfficeTO.OfficeTOBuilder().
                withAddress(address).withName(name).
                withPhoneNumber(phoneNumber).build();

        return officeTO;
    }




}
