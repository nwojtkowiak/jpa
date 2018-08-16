package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.ColorDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.TypeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ColorEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.TypeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.ColorMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.TypeMapper;
import com.capgemini.service.CarService;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.CarTO;
import com.capgemini.types.ColorTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.TypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<EmployeeTO> findAllEmployees() {
        return EmployeeMapper.map2TOs(employeeDao.findAll());
    }

    @Override
    public EmployeeTO findEmployeeById(long id) {
        return EmployeeMapper.toTO(employeeDao.findOne(id));
    }

    @Override
    @Transactional(readOnly = false)
    public EmployeeTO addEmployee(EmployeeTO employee) {
        EmployeeEntity employeeEntity = EmployeeMapper.toEntity(employee);
        return EmployeeMapper.toTO(employeeDao.save(employeeEntity));
    }

    @Override
    public void deleteEmployee(long employee_id) {
        employeeDao.deleteEmployee(employee_id);
    }
}
