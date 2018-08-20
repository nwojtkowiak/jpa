package com.capgemini.dao.impl;

import com.capgemini.dao.CustomerDao;
import com.capgemini.dao.impl.AbstractDao;
import com.capgemini.domain.CustomerEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.types.EmployeeSearchCriteriaTO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CustomerDaoImpl extends AbstractDao<CustomerEntity, Long> implements CustomerDao {



}
