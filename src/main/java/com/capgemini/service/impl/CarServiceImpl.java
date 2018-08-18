package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.dao.ColorDao;
import com.capgemini.dao.LoanDao;
import com.capgemini.dao.TypeDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.domain.ColorEntity;
import com.capgemini.domain.LoanEntity;
import com.capgemini.domain.TypeEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.ColorMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.mappers.TypeMapper;
import com.capgemini.service.CarService;
import com.capgemini.types.CarTO;
import com.capgemini.types.ColorTO;
import com.capgemini.types.EmployeeTO;
import com.capgemini.types.TypeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private TypeDao typeDao;

    //TODO zamienić na serwis
    @Autowired
    private LoanDao loanDao;

    @Override
    public List<CarTO> findAllCars() {
        return CarMapper.map2TOs(carDao.findAll());
    }

    @Override
    public List<CarTO> findCarsByKeeper(long employee_id) {
        List<CarEntity> lisOfCars = carDao.findCarsByKeeper(employee_id);
        if (lisOfCars != null) {
            return CarMapper.map2TOs(lisOfCars);
        }
        return new ArrayList<>();
    }

    @Override
    public List<CarTO> findCarsByTypeAndMark(String type, String mark) {
        return CarMapper.map2TOs(carDao.findCarsByTypeAndMark(type, mark));
    }

    @Override
    public CarTO findCarById(long car_id) {
        return CarMapper.toTO(carDao.findOne(car_id));
    }

    @Override
    @Transactional(readOnly = false)
    public void addKeeper(CarTO carTO, EmployeeTO employeeTO) {

        carDao.addKeeper(CarMapper.toEntity(carTO), EmployeeMapper.toEntity(employeeTO));
    }

    @Override
    @Transactional(readOnly = false)
    public CarTO addCar(CarTO car) {
        ColorEntity colorEntity = colorDao.findOne(car.getColor());
        colorEntity = colorDao.add(colorEntity);

        TypeEntity typeEntity = typeDao.findOne(car.getType());
        typeEntity = typeDao.add(typeEntity);

        CarEntity carEntity = CarMapper.toEntity(car);
        carEntity.setColor(colorEntity);
        carEntity.setType(typeEntity);

        CarTO carTO = CarMapper.toTO(carDao.add(carEntity));
        return carTO;
    }

    @Override
    @Transactional(readOnly = false)
    public ColorTO addColor(ColorTO colorTO) {
        ColorEntity colorEntity = ColorMapper.toEntity(colorTO);
        return ColorMapper.toTO(colorDao.save(colorEntity));
    }

    @Override
    public List<ColorTO> findAllColors() {
        return ColorMapper.map2TOs(colorDao.findAll());
    }

    @Override
    @Transactional(readOnly = false)
    public CarTO updateCar(CarTO car) {
        CarEntity carEntity = CarMapper.toEntity(car);

        carEntity.setColor(colorDao.findOne(car.getColor()));
        carEntity.setType(typeDao.findOne(car.getType()));
        List<LoanEntity> loans = new LinkedList<>();
        for(Long loanId : car.getLoans()){
            loans.add(loanDao.findOne(loanId));
        }
        return CarMapper.toTO(carDao.update(carEntity));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCar(long car_id) {
        carDao.deleteCar(car_id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteColor(long color_id) {
        colorDao.delete(color_id);
    }

    @Override
    @Transactional(readOnly = false)
    public TypeTO addType(TypeTO type) {
        TypeEntity typeEntity = TypeMapper.toEntity(type);
        return TypeMapper.toTO(typeDao.save(typeEntity));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteType(long type_id) {
        typeDao.delete(type_id);
    }

}
