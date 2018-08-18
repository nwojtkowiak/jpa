package com.capgemini.service.impl;

import com.capgemini.dao.AddressDao;
import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.OfficeEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.OfficeMapper;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.OfficeService;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.OfficeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeDao officeDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EmployeeService employeeService;

    @Override
    @Transactional(readOnly = false)
    public OfficeTO addOffice(OfficeTO office) {
        AddressEntity addressEntity = addressDao.findOne(office.getAddress());
        addressEntity = addressDao.add(addressEntity);

        OfficeEntity officeEntity = OfficeMapper.toEntity(office);
        officeEntity.setAddress(addressEntity);

        return OfficeMapper.toTO(officeDao.add(officeEntity));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteOffice(long id) {
        officeDao.delete(id);
    }


    @Override
    @Transactional(readOnly = false)
    public OfficeTO updateOffice(OfficeTO office) {
        OfficeEntity officeEntity = OfficeMapper.toEntity(office);
        officeEntity.setAddress(addressDao.findOne(office.getAddress()));
        return OfficeMapper.toTO(officeDao.update(officeEntity));
    }

    @Override
    public void addEmployeeToOffice(EmployeeTO employee, OfficeTO office) {
        employeeService.addOfficeToEmployee(employee.getId(), office.getId());
    }

    @Override
    public void delEmployeeFromOffice(long employee_id, long office_id) {
        employeeService.delOfficeFromEmployee(employee_id,office_id);
    }

    @Override
    public List<EmployeeTO> findEmployeesByOffice(long office_id) {
      return employeeService.findEmployeeByOfficeId(office_id);
    }

    @Override
    public List<EmployeeTO> findEmployeeByOfficeAndCar(long office_id, long car_id) {
        return employeeService.findEmployeeByOfficeIdAndCarId(office_id,car_id);
    }

    @Override
    public OfficeTO findOfficeById(long office_id) {

        return OfficeMapper.toTO(officeDao.findOne(office_id));
    }
}
