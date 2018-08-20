package com.capgemini.dao.impl;

import com.capgemini.dao.LoanDao;
import com.capgemini.dao.TypeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.LoanEntity;
import com.capgemini.domain.TypeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class LoanDaoImpl extends AbstractDao<LoanEntity, Long> implements LoanDao {

    @Override
    public List<CarEntity> findCarsWith10Loans(){
        Query query = entityManager.createQuery(
                "select c from CarEntity c " +
                " where c.id in (" +
                " select  l.car.id from LoanEntity l " +
                " where l.car.id = c.id " +
                " group by l.car.id " +
                " having count(distinct l.customer.id) >= 10) ");

        try{
            return query.getResultList();
        }catch (NoResultException e){
            return new ArrayList<>();
        }
    }

}
