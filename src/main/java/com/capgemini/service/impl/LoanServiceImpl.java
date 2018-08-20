package com.capgemini.service.impl;

import com.capgemini.dao.*;
import com.capgemini.domain.*;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.CustomerMapper;
import com.capgemini.mappers.LoanMapper;
import com.capgemini.service.LoanService;
import com.capgemini.types.CarTO;
import com.capgemini.types.CustomerTO;
import com.capgemini.types.LoanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private OfficeDao officeDao;

    @Override
    public List<CarTO> findCarsLoanedByMoreThanPeople() {
        return CarMapper.map2TOs(loanDao.findCarsWith10Loans());
    }

    @Override
    public List<Long> findAllLoans() {
        return LoanMapper.map2TOs(loanDao.findAll());
    }

    @Override
    public Long countCarsWithLoansBetweenDate(String from, String to) {
        return loanDao.countCarsWithLoansBetweenDate(from, to);
    }

    /**
     * This method adds a new customer to database
     * @param customer - transport object without id
     * @return - transport object of customer with new id
     */
    @Override
    public CustomerTO addCustomer(CustomerTO customer) {
        AddressEntity addressEntity = addressDao.findOne(customer.getAddress());

        CustomerEntity customerEntity = CustomerMapper.toEntity(customer);
        customerEntity.setAddress(addressEntity);

        return CustomerMapper.toTO(customerDao.save(customerEntity));
    }

    @Override
    public List<Long> findAllCustomers() {
        return CustomerMapper.map2TOs(customerDao.findAll());
    }

    /**
     * This method adds new loan to database
     * Before save this loan to database every need entity saved:
     * CustomerEntity, CarEntity, OfficeEntity (from), OfficeEntity (to)
     * After save new loan, this loan is saving to every related entity;
     * @param loan - transport object without id
     * @return - transport object of loan with new id
     */
    @Override
    public LoanTO addLoan(LoanTO loan) {
        CustomerEntity customerEntity = customerDao.findOne(loan.getCustomer());
        CarEntity carEntity = carDao.findOne(loan.getCar());
        OfficeEntity officeEntityFrom = officeDao.findOne(loan.getOfficeFrom());
        OfficeEntity officeEntityTo = officeDao.findOne(loan.getOfficeTo());

        LoanEntity loanEntity = LoanMapper.toEntity(loan);
        loanEntity.setCustomer(customerEntity);
        loanEntity.setCar(carEntity);
        loanEntity.setOfficeFrom(officeEntityFrom);
        loanEntity.setOfficeTo(officeEntityTo);
        loanEntity = loanDao.save(loanEntity);

        carEntity.getLoans().add(loanEntity);
        carDao.update(carEntity);
        customerEntity.getLoans().add(loanEntity);
        customerDao.update(customerEntity);
        officeEntityFrom.getLoansFrom().add(loanEntity);
        officeDao.update(officeEntityFrom);
        officeEntityTo.getLoansTo().add(loanEntity);
        officeDao.update(officeEntityTo);

        return LoanMapper.toTO(loanEntity);
    }
}
