package com.capgemini.service;


import com.capgemini.types.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.capgemini.service.HelpMethods.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private CarService carService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OfficeService officeService;

    @Test
    @Transactional
    public void testShouldFindCarsWithLoans() {
        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO car1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car1 = carService.addCar(car1);

        CarTO car2 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car2 = carService.addCar(car2);


        //adresy
        AddressTO address = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        address = addressService.addAddress(address);

        AddressTO addressOffice = new AddressTO.AddressTOBuilder().withStreet("Zimowa").withBuilding(5555).withFlat(1).withCity("Poznan").withPostCode("61-456").build();
        addressOffice = addressService.addAddress(addressOffice);

        //klienci
        CustomerTO customer1 = createCustomer("Felek", "F", "1961-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer1 = loanService.addCustomer(customer1);

        CustomerTO customer2 = createCustomer("Michał", "M", "1962-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer2 = loanService.addCustomer(customer2);

        CustomerTO customer3 = createCustomer("Bartek", "B", "1963-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer3 = loanService.addCustomer(customer3);

        CustomerTO customer4 = createCustomer("Piotr", "P", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer4 = loanService.addCustomer(customer4);

        CustomerTO customer5 = createCustomer("Adam", "A", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer5 = loanService.addCustomer(customer5);

        CustomerTO customer6 = createCustomer("Rambo", "R", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer6 = loanService.addCustomer(customer6);

        CustomerTO customer7 = createCustomer("Witold", "W", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer7 = loanService.addCustomer(customer7);

        CustomerTO customer8 = createCustomer("Gabriela", "G", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer8 = loanService.addCustomer(customer8);

        CustomerTO customer9 = createCustomer("Ryszard", "R", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer9 = loanService.addCustomer(customer9);

        CustomerTO customer10 = createCustomer("Lidia", "L", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer10 = loanService.addCustomer(customer10);

        CustomerTO customer11 = createCustomer("Małgorzata", "M", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer11 = loanService.addCustomer(customer11);

        //biuro
        OfficeTO office = createOffice("BiuroZ", "700122333", addressOffice.getId());
        office = officeService.addOffice(office);

        //wypożyczenia
        LoanTO loan1 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-10", "2018-08-12", customer1.getId(), 1000);
        loanService.addLoan(loan1);
        LoanTO loan2 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-09-10", "2018-09-12", customer2.getId(), 1000);
        loanService.addLoan(loan2);
        LoanTO loan3 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-10", "2018-08-19", customer3.getId(), 1000);
        loanService.addLoan(loan3);
        LoanTO loan4 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-01", "2018-08-12", customer4.getId(), 1000);
        loanService.addLoan(loan4);
        LoanTO loan5 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-09-10", "2018-09-12", customer5.getId(), 1000);
        loanService.addLoan(loan5);
        LoanTO loan6 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-10-10", "2018-10-12", customer6.getId(), 1000);
        loanService.addLoan(loan6);
        LoanTO loan7 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-01-10", "2018-01-12", customer7.getId(), 1000);
        loanService.addLoan(loan7);
        LoanTO loan8 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-02-10", "2018-02-12", customer8.getId(), 1000);
        loanService.addLoan(loan8);
        LoanTO loan9 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-03-10", "2018-03-12", customer9.getId(), 1000);
        loanService.addLoan(loan9);
        LoanTO loan10 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-04-10", "2018-04-12", customer10.getId(), 1000);
        loanService.addLoan(loan10);
        LoanTO loan11 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-05-10", "2018-05-12", customer11.getId(), 1000);
        loanService.addLoan(loan11);
        LoanTO loan12 = createLoan(office.getId(), office.getId(), car2.getId(), "2018-06-10", "2018-06-12", customer11.getId(), 1000);
        loanService.addLoan(loan12);

        // when
        List<CarTO> foundCars = loanService.findCarsLoanedByMoreThanPeople();

        // then
        assertNotNull(foundCars);
        assertEquals(1, foundCars.size());
        assertEquals("Opel", foundCars.get(0).getMark());

    }

    @Test
    @Transactional
    public void testShouldFindCarsWithLoansBetweenDate() {
        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO car1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car1 = carService.addCar(car1);

        CarTO car2 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car2 = carService.addCar(car2);

        CarTO car3 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car3 = carService.addCar(car3);

        CarTO car4 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car4 = carService.addCar(car4);

        CarTO car5 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car5 = carService.addCar(car5);

        CarTO car6 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car6 = carService.addCar(car6);

        CarTO car7 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car7 = carService.addCar(car7);

        CarTO car8 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car8 = carService.addCar(car8);

        CarTO car9 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car9 = carService.addCar(car9);

        CarTO car10 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car10 = carService.addCar(car10);


        //adresy
        AddressTO address = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        address = addressService.addAddress(address);

        AddressTO addressOffice = new AddressTO.AddressTOBuilder().withStreet("Zimowa").withBuilding(5555).withFlat(1).withCity("Poznan").withPostCode("61-456").build();
        addressOffice = addressService.addAddress(addressOffice);

        //klienci
        CustomerTO customer1 = createCustomer("Felek", "F", "1961-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer1 = loanService.addCustomer(customer1);

        CustomerTO customer2 = createCustomer("Michał", "M", "1962-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer2 = loanService.addCustomer(customer2);

        CustomerTO customer3 = createCustomer("Bartek", "B", "1963-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer3 = loanService.addCustomer(customer3);

        CustomerTO customer4 = createCustomer("Piotr", "P", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer4 = loanService.addCustomer(customer4);

        CustomerTO customer5 = createCustomer("Adam", "A", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer5 = loanService.addCustomer(customer5);

        CustomerTO customer6 = createCustomer("Rambo", "R", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer6 = loanService.addCustomer(customer6);

        CustomerTO customer7 = createCustomer("Witold", "W", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer7 = loanService.addCustomer(customer7);

        CustomerTO customer8 = createCustomer("Gabriela", "G", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer8 = loanService.addCustomer(customer8);

        CustomerTO customer9 = createCustomer("Ryszard", "R", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer9 = loanService.addCustomer(customer9);

        CustomerTO customer10 = createCustomer("Lidia", "L", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer10 = loanService.addCustomer(customer10);

        CustomerTO customer11 = createCustomer("Małgorzata", "M", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer11 = loanService.addCustomer(customer11);

        //biuro
        OfficeTO office = createOffice("BiuroZ", "700122333", addressOffice.getId());
        office = officeService.addOffice(office);

        //wypożyczenia
        LoanTO loan1 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-10", "2018-08-12", customer1.getId(), 1000);
        loanService.addLoan(loan1);
        LoanTO loan2 = createLoan(office.getId(), office.getId(), car2.getId(), "2018-09-10", "2018-09-12", customer2.getId(), 1000);
        loanService.addLoan(loan2);
        LoanTO loan3 = createLoan(office.getId(), office.getId(), car3.getId(), "2018-08-10", "2018-08-19", customer3.getId(), 1000);
        loanService.addLoan(loan3);
        LoanTO loan4 = createLoan(office.getId(), office.getId(), car4.getId(), "2018-08-01", "2018-08-12", customer4.getId(), 1000);
        loanService.addLoan(loan4);
        LoanTO loan5 = createLoan(office.getId(), office.getId(), car5.getId(), "2018-09-10", "2018-09-12", customer5.getId(), 1000);
        loanService.addLoan(loan5);
        LoanTO loan6 = createLoan(office.getId(), office.getId(), car6.getId(), "2018-10-10", "2018-10-12", customer6.getId(), 1000);
        loanService.addLoan(loan6);
        LoanTO loan7 = createLoan(office.getId(), office.getId(), car7.getId(), "2018-01-10", "2018-01-12", customer7.getId(), 1000);
        loanService.addLoan(loan7);
        LoanTO loan8 = createLoan(office.getId(), office.getId(), car8.getId(), "2018-02-10", "2018-02-12", customer8.getId(), 1000);
        loanService.addLoan(loan8);
        LoanTO loan9 = createLoan(office.getId(), office.getId(), car9.getId(), "2018-03-10", "2018-03-12", customer9.getId(), 1000);
        loanService.addLoan(loan9);
        LoanTO loan10 = createLoan(office.getId(), office.getId(), car10.getId(), "2018-04-10", "2018-04-12", customer10.getId(), 1000);
        loanService.addLoan(loan10);
        LoanTO loan11 = createLoan(office.getId(), office.getId(), car10.getId(), "2018-05-10", "2018-05-12", customer11.getId(), 1000);
        loanService.addLoan(loan11);
        LoanTO loan12 = createLoan(office.getId(), office.getId(), car10.getId(), "2018-06-10", "2018-06-12", customer11.getId(), 1000);
        loanService.addLoan(loan12);

        // when
        Long count = loanService.countCarsWithLoansBetweenDate("2018-06-01", "2018-08-15");
        final Long result = 3L;
        // then
        assertEquals(result, count);

    }


    @Test
    @Transactional
    public void testShould1LoansAfterDeleteCar() {
        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        //samochody
        CarTO car1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car1 = carService.addCar(car1);

        CarTO car2 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car2 = carService.addCar(car2);


        //adresy
        AddressTO address = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        address = addressService.addAddress(address);

        AddressTO addressOffice = new AddressTO.AddressTOBuilder().withStreet("Zimowa").withBuilding(5555).withFlat(1).withCity("Poznan").withPostCode("61-456").build();
        addressOffice = addressService.addAddress(addressOffice);

        //klienci
        CustomerTO customer1 = createCustomer("Felek", "F", "1961-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer1 = loanService.addCustomer(customer1);

        CustomerTO customer2 = createCustomer("Michał", "M", "1962-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer2 = loanService.addCustomer(customer2);

        CustomerTO customer3 = createCustomer("Bartek", "B", "1963-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer3 = loanService.addCustomer(customer3);

        CustomerTO customer4 = createCustomer("Piotr", "P", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer4 = loanService.addCustomer(customer4);

        CustomerTO customer5 = createCustomer("Adam", "A", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer5 = loanService.addCustomer(customer5);

        CustomerTO customer6 = createCustomer("Rambo", "R", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer6 = loanService.addCustomer(customer6);

        CustomerTO customer7 = createCustomer("Witold", "W", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer7 = loanService.addCustomer(customer7);

        CustomerTO customer8 = createCustomer("Gabriela", "G", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer8 = loanService.addCustomer(customer8);

        CustomerTO customer9 = createCustomer("Ryszard", "R", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer9 = loanService.addCustomer(customer9);

        CustomerTO customer10 = createCustomer("Lidia", "L", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer10 = loanService.addCustomer(customer10);

        CustomerTO customer11 = createCustomer("Małgorzata", "M", "1960-05-02", address.getId(), "752369123", "rambo@wp.pl", "451236985223");
        customer11 = loanService.addCustomer(customer11);

        //biuro
        OfficeTO office = createOffice("BiuroZ", "700122333", addressOffice.getId());
        office = officeService.addOffice(office);

        //wypożyczenia
        LoanTO loan1 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-10", "2018-08-12", customer1.getId(), 1000);
        loanService.addLoan(loan1);
        LoanTO loan2 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-09-10", "2018-09-12", customer2.getId(), 1000);
        loanService.addLoan(loan2);
        LoanTO loan3 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-10", "2018-08-19", customer3.getId(), 1000);
        loanService.addLoan(loan3);
        LoanTO loan4 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-08-01", "2018-08-12", customer4.getId(), 1000);
        loanService.addLoan(loan4);
        LoanTO loan5 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-09-10", "2018-09-12", customer5.getId(), 1000);
        loanService.addLoan(loan5);
        LoanTO loan6 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-10-10", "2018-10-12", customer6.getId(), 1000);
        loanService.addLoan(loan6);
        LoanTO loan7 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-01-10", "2018-01-12", customer7.getId(), 1000);
        loanService.addLoan(loan7);
        LoanTO loan8 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-02-10", "2018-02-12", customer8.getId(), 1000);
        loanService.addLoan(loan8);
        LoanTO loan9 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-03-10", "2018-03-12", customer9.getId(), 1000);
        loanService.addLoan(loan9);
        LoanTO loan10 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-04-10", "2018-04-12", customer10.getId(), 1000);
        loanService.addLoan(loan10);
        LoanTO loan11 = createLoan(office.getId(), office.getId(), car1.getId(), "2018-05-10", "2018-05-12", customer11.getId(), 1000);
        loanService.addLoan(loan11);
        LoanTO loan12 = createLoan(office.getId(), office.getId(), car2.getId(), "2018-06-10", "2018-06-12", customer11.getId(), 1000);
        loanService.addLoan(loan12);


        List<Long> foundLoans = loanService.findAllLoans();
        assertEquals(12, foundLoans.size());

        carService.deleteCar(car1.getId());

        // when
        foundLoans = loanService.findAllLoans();

        // then
        assertNotNull(foundLoans);
        assertEquals(1, foundLoans.size());

        List<Long> foundCustomers = loanService.findAllCustomers();
        assertNotNull(foundCustomers);
        assertEquals(11, foundCustomers.size());

        OfficeTO foundOffice = officeService.findOfficeById(office.getId());
        assertNotNull(foundOffice);

    }


}
