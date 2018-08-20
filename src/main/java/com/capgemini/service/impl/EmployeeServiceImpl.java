package com.capgemini.service.impl;

import com.capgemini.dao.AddressDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.EmployeeEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.domain.PositionEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.PositionMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.types.EmployeeSearchCriteriaTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.PositionTO;
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
    public EmployeeTO findEmployeeById(long id) {

        return EmployeeMapper.toTO(employeeDao.findOne(id));
    }

    @Override
    public List<EmployeeTO> findAllEmployee() {
        return EmployeeMapper.map2TOs(employeeDao.findAll());
    }

    @Override
    public List<EmployeeTO> findEmployeeByOfficeId(long office_id) {

        return EmployeeMapper.map2TOs(employeeDao.findAllByOfficeId(office_id));
    }

    @Override
    public List<EmployeeTO> findEmployeeByOfficeIdAndCarId(long office_id, long car_id) {

        return EmployeeMapper.map2TOs(employeeDao.findAllByOfficeIdAndCarId(office_id, car_id));
    }

    @Override
    public List<EmployeeTO> findEmployeeByCriteria(EmployeeSearchCriteriaTO criteria) {
        return EmployeeMapper.map2TOs(employeeDao.findAllByEmployeeCriteria(criteria));
    }

    /**
     * This method adds new employee to database
     * The employee address and office and position are saved before employee
     * @param employee - transport object without if
     * @return transport object of employee with new id
     */
    @Override
    @Transactional(readOnly = false)
    public EmployeeTO addEmployee(EmployeeTO employee) {
        AddressEntity addressEntity = addressDao.findOne(employee.getAddress());

        OfficeEntity officeEntity = null;

        if (employee.getOffice() != null) {
            officeEntity = officeDao.findOne(employee.getOffice());
        }

        PositionEntity positionEntity = positionDao.findOne(employee.getPosition());

        if (addressEntity != null && positionEntity != null) {
            EmployeeEntity employeeEntity = EmployeeMapper.toEntity(employee);
            employeeEntity.setAddress(addressEntity);
            employeeEntity.setOffice(officeEntity);
            employeeEntity.setPosition(positionEntity);
            employeeEntity = employeeDao.save(employeeEntity);

            if (officeEntity != null) {
                officeEntity.getEmployees().add(employeeEntity);
                officeDao.update(officeEntity);
            }
            return EmployeeMapper.toTO(employeeEntity);
        }
        return employee;

    }

    @Override
    @Transactional(readOnly = false)
    public void deleteEmployee(long employee_id) {
        employeeDao.delete(employee_id);
    }


    /**
     * This method sets office for employee and adds employee to office
     * @param employee_id - employee id
     * @param office_id - office if
     * @return transport object with updated employee information
     */
    @Override
    @Transactional(readOnly = false)
    public EmployeeTO addOfficeToEmployee(Long employee_id, Long office_id) {
        OfficeEntity officeEntity = officeDao.findOne(office_id);
        EmployeeEntity employeeEntity = employeeDao.findOne(employee_id);
        if (officeEntity != null && employeeEntity != null) {
            employeeEntity.setOffice(officeEntity);
            employeeEntity = employeeDao.updateEmployeeInfo(employeeEntity);
            officeEntity.getEmployees().add(employeeEntity);
            officeDao.update(officeEntity);
        }

        return EmployeeMapper.toTO(employeeEntity);
    }

    /**
     * This method deletes office from employee and office
     * @param employee_id - employee id
     * @param office_id - office id
     * @return transport object with updated employee information
     */
    @Override
    @Transactional(readOnly = false)
    public EmployeeTO deleteOfficeFromEmployee(Long employee_id, Long office_id) {
        OfficeEntity officeEntity = officeDao.findOne(office_id);
        EmployeeEntity employeeEntity = employeeDao.findOne(employee_id);
        if (officeEntity != null && employeeEntity != null) {
            employeeEntity.setOffice(null);
            employeeEntity = employeeDao.updateEmployeeInfo(employeeEntity);
            officeEntity.getEmployees().remove(employeeEntity);
            officeDao.update(officeEntity);
        }
        return EmployeeMapper.toTO(employeeEntity);
    }


    @Override
    @Transactional(readOnly = false)
    public PositionTO addPosition(PositionTO position) {
        PositionEntity positionEntity = PositionMapper.toEntity(position);
        return PositionMapper.toTO(positionDao.add(positionEntity));
    }


}
