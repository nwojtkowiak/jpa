package com.capgemini.service;

import com.capgemini.types.CarTO;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.LoanTO;

import java.util.List;


public interface LoanService {

    LoanTO addLoan(LoanTO loan);

    List<CarTO> findCarsLoanedByMoreThanPeople();

    List<Long> findAllLoans();

    Long countCarsWithLoansBetweenDate(String from, String to);

    CustomerTO addCustomer(CustomerTO customer);

    List<Long> findAllCustomers();


}
