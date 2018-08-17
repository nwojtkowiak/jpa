package com.capgemini.service.impl;

import com.capgemini.dao.*;
import com.capgemini.domain.*;
import com.capgemini.mappers.*;
import com.capgemini.service.CarService;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private PositionDao positionDao;

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
        AddressEntity addressEntity = AddressMapper.toEntity(employee.getAddress());
        addressEntity = addressDao.add(addressEntity);

        OfficeEntity officeEntity = OfficeMapper.toEntity(employee.getOffice());
        addressDao.add(officeEntity.getAddress());
        officeEntity = officeDao.add(officeEntity);

        PositionEntity positionEntity = PositionMapper.toEntity(employee.getPosition());
        positionEntity = positionDao.add(positionEntity);

        EmployeeEntity employeeEntity = EmployeeMapper.toEntity(employee);
        employeeEntity.setAddress(addressEntity);
        employeeEntity.setOffice(officeEntity);
        employeeEntity.setPosition(positionEntity);

        return EmployeeMapper.toTO(employeeDao.save(employeeEntity));
    }

    @Override
    public void deleteEmployee(long employee_id) {
        employeeDao.deleteEmployee(employee_id);
    }


}
