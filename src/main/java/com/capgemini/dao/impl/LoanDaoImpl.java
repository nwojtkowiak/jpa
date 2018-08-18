package com.capgemini.dao.impl;

import com.capgemini.dao.LoanDao;
import com.capgemini.dao.TypeDao;
import com.capgemini.domain.LoanEntity;
import com.capgemini.domain.TypeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Repository
public class LoanDaoImpl extends AbstractDao<LoanEntity, Long> implements LoanDao {


}
