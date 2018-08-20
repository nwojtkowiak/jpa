package com.capgemini.dao.impl;

import com.capgemini.dao.LoanDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.LoanEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Repository
public class LoanDaoImpl extends AbstractDao<LoanEntity, Long> implements LoanDao {

    /**
     * This methods finds cars which were loaned more than 10 times by different customers
     * @return list of CarEntity or empty list
     */
    @Override
    public List<CarEntity> findCarsWith10Loans() {
        Query query = entityManager.createQuery(
                "select c from CarEntity c " +
                        " where c.id in (" +
                        " select  l.car.id from LoanEntity l " +
                        " where l.car.id = c.id " +
                        " group by l.car.id " +
                        " having count(distinct l.customer.id) >= 10) ");

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return new LinkedList<>();
        }
    }

    /**
     * This method counts cars which were loaned between from and to date
     * @param from date which is start of a period
     * @param to date which is end of a period
     * @return number of loans
     */
    @Override
    public Long countCarsWithLoansBetweenDate(String from, String to) {
        Query query = entityManager.createQuery(
                "select count(distinct l.car.id) from LoanEntity l " +
                        " where l.dateFrom >= :dateFrom  and l.dateTo <= :dateTo");

        query.setParameter("dateFrom", Date.valueOf(from));
        query.setParameter("dateTo", Date.valueOf(to));


        return (Long) query.getSingleResult();


    }


}
