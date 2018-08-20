package com.capgemini.service;

import com.capgemini.types.CarTO;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.LoanTO;

import java.util.List;


public interface LoanService {

    List<CarTO> findCarsLoanedByMoreThanPeople();
    CustomerTO addCustomer(CustomerTO customer);
    LoanTO addLoan(LoanTO loan);

}
