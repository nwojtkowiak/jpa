package com.capgemini.service.impl;

import com.capgemini.dao.CarDao;
import com.capgemini.domain.CarEntity;
import com.capgemini.mappers.CarMapper;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.types.CarTO;
import com.capgemini.types.EmployeeTO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CarServiceImpl implements CarService {
    @Autowired
    CarDao carDao;

    @Override
    public List<CarTO> findAllCars() {
        return CarMapper.map2TOs(carDao.findAll());
    }

    @Override
    public List<CarTO> findCarsByKeeper(long employee_id) {
        return CarMapper.map2TOs(carDao.findCarsByKeeper(employee_id));
    }

    @Override
    public List<CarTO> findCarsByTypeAndMark(String type, String mark) {
        return CarMapper.map2TOs(carDao.findCarsByTypeAndMark(type, mark));
    }

    @Override
    public CarTO findCarById(long car_id) {
        return CarMapper.toTO(carDao.findCarById(car_id));
    }

    @Override
    public void addKeeper( CarTO carTO,EmployeeTO employeeTO) {
         carDao.addKeeper(CarMapper.toEntity(carTO), EmployeeMapper.toEntity(employeeTO));
    }

    @Override
    public CarTO addCar(CarTO car) {
        CarEntity carEntity = CarMapper.toEntity(car);
        return CarMapper.toTO(carDao.save(carEntity));
    }

    @Override
    public CarTO updateCar(CarTO car) {
        CarEntity carEntity = CarMapper.toEntity(car);
        return CarMapper.toTO(carDao.save(carEntity));
    }

    @Override
    public void deleteCar(long car_id) {
        carDao.deleteCar(car_id);
    }

}
