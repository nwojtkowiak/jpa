package com.capgemini.service.impl;

import com.capgemini.dao.AddressDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.mappers.PositionMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeTO;
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
    public List<EmployeeTO> findEmployeeByOfficeId(long office_id) {

        return EmployeeMapper.map2TOs(employeeDao.findAllByOfficeId(office_id));
    }

    @Override
    public List<EmployeeTO> findEmployeeByOfficeIdAndCarId(long office_id, long car_id) {

        return EmployeeMapper.map2TOs(employeeDao.findAllByOfficeIdAndCarId(office_id,car_id));
    }

    @Override
    @Transactional(readOnly = false)
    public EmployeeTO addEmployee(EmployeeTO employee) {
        AddressEntity addressEntity = AddressMapper.toEntity(employee.getAddress());
        addressEntity = addressDao.add(addressEntity);

        OfficeEntity officeEntity = null;

        if(employee.getOffice() != null) {
            officeEntity =OfficeMapper.toEntity(employee.getOffice());
            addressDao.add(officeEntity.getAddress());
            officeEntity = officeDao.add(officeEntity);
        }

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

    @Override
    public EmployeeTO addOfficeToEmployee(Long employee_id, Long office_id) {
        EmployeeEntity employeeEntity = employeeDao.setOffice(employee_id, office_id);
        return EmployeeMapper.toTO(employeeEntity);
    }

    @Override
    public EmployeeTO delOfficeFromEmployee(Long employee_id, Long office_id) {
        EmployeeEntity employeeEntity = employeeDao.removeOffice(employee_id);
        return EmployeeMapper.toTO(employeeEntity);
    }


}
