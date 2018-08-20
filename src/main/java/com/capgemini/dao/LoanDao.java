package com.capgemini.dao;

import com.capgemini.domain.CarEntity;
import com.capgemini.domain.LoanEntity;

import java.util.List;

public interface LoanDao extends Dao<LoanEntity, Long> {

    List<CarEntity> findCarsWith10Loans();

    Long countCarsWithLoansBetweenDate(String from, String to);
}
