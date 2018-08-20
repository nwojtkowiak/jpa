package com.capgemini.service;


import com.capgemini.types.*;
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
public class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private CarService carService;

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
    public void testShouldFindCarsWithLoans() {
        // given
        ColorTO colorBlack = createColor("black");
        colorBlack = carService.addColor(colorBlack);

        TypeTO typeHatchback = createType("hatchback");
        typeHatchback = carService.addType(typeHatchback);

        TypeTO typeCombi = createType("combi");
        typeCombi = carService.addType(typeCombi);


        //samochody
        CarTO car1 = createCar("Opel", "Corsa", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car1 = carService.addCar(car1);

        CarTO car2 = createCar("Nissan", "Almera", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeHatchback.getId());
        car2 = carService.addCar(car2);

        CarTO car3 = createCar("Peugeot", "206", 2002, 1.4d, 60,
                234500, colorBlack.getId(), typeCombi.getId());
        car3 = carService.addCar(car3);

        //adresy
        AddressTO address = new AddressTO.AddressTOBuilder().withStreet("Kolorowa").withBuilding(6).withFlat(1).withCity("Poznan").withPostCode("61-852").build();
        address = addressService.addAddress(address);

        AddressTO addressOffice = new AddressTO.AddressTOBuilder().withStreet("Zimowa").withBuilding(5555).withFlat(1).withCity("Poznan").withPostCode("61-456").build();
        addressOffice = addressService.addAddress(addressOffice);

        //klienci
        CustomerTO customer1 = createCustomer("Felek","F",new Date(19610502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer1 = loanService.addCustomer(customer1);

        CustomerTO customer2 = createCustomer("Michał","M",new Date(19620502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer2 = loanService.addCustomer(customer2);

        CustomerTO customer3 = createCustomer("Bartek","B",new Date(19630502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer3 = loanService.addCustomer(customer3);

        CustomerTO customer4 = createCustomer("Piotr","P",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer4 = loanService.addCustomer(customer4);

        CustomerTO customer5 = createCustomer("Adam","A",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer5 = loanService.addCustomer(customer5);

        CustomerTO customer6 = createCustomer("Rambo","R",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer6 = loanService.addCustomer(customer6);

        CustomerTO customer7 = createCustomer("Witold","W",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer7 = loanService.addCustomer(customer7);

        CustomerTO customer8 = createCustomer("Gabriela","G",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer8 = loanService.addCustomer(customer8);

        CustomerTO customer9 = createCustomer("Ryszard","R",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer9 = loanService.addCustomer(customer9);

        CustomerTO customer10 = createCustomer("Lidia","L",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer10 = loanService.addCustomer(customer10);

        CustomerTO customer11 = createCustomer("Małgorzata","M",new Date(19600502), address.getId(),"752369123","rambo@wp.pl","451236985223");
        customer11 = loanService.addCustomer(customer11);

        //biuro
        OfficeTO office = createOffice("BiuroZ","700122333", addressOffice.getId());
        office = officeService.addOffice(office);


        //wypożyczenia
        LoanTO loan1 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180810), new Date(20180812),customer1.getId(), 1000);
        loan1 = loanService.addLoan(loan1);
        LoanTO loan2 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180910), new Date(20180912),customer2.getId(), 1000);
        loan2 = loanService.addLoan(loan2);
        LoanTO loan3 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180810), new Date(20180819),customer3.getId(), 1000);
        loan3 = loanService.addLoan(loan3);
        LoanTO loan4 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180801), new Date(20180812),customer4.getId(), 1000);
        loan4 = loanService.addLoan(loan4);
        LoanTO loan5 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180910), new Date(20180912),customer5.getId(), 1000);
        loan5 = loanService.addLoan(loan5);
        LoanTO loan6 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20181010), new Date(20181012),customer6.getId(), 1000);
        loan6 = loanService.addLoan(loan6);
        LoanTO loan7 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180110), new Date(20180112),customer7.getId(), 1000);
        loan7 = loanService.addLoan(loan7);
        LoanTO loan8 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180210), new Date(20180212),customer8.getId(), 1000);
        loan8 = loanService.addLoan(loan8);
        LoanTO loan9 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180310), new Date(20180312),customer9.getId(), 1000);
        loan9 = loanService.addLoan(loan9);
        LoanTO loan10 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180410), new Date(20180412),customer10.getId(), 1000);
        loan10 = loanService.addLoan(loan10);
        LoanTO loan11 = createLoan(office.getId(),office.getId(),car1.getId(),new Date(20180510), new Date(20180512),customer11.getId(), 1000);
        loan11 = loanService.addLoan(loan11);
        LoanTO loan12 = createLoan(office.getId(),office.getId(),car2.getId(),new Date(20180610), new Date(20180612),customer11.getId(), 1000);
        loan12 = loanService.addLoan(loan12);

        // when
        List<CarTO> foundCars = loanService.findCarsLoanedByMoreThanPeople();

        // then
        assertNotNull(foundCars);
        assertEquals(1, foundCars.size());
        assertEquals("Opel", foundCars.get(0).getMark());

    }




}
