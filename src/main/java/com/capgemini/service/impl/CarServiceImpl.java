package com.capgemini.service.impl;

import com.capgemini.dao.*;
import com.capgemini.domain.*;
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

    @Autowired
    private LoanDao loanDao;

    @Autowired
    private EmployeeDao employeeDao;

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

    /**
     * This method adds a keeper to a car
     * If gave keeper or car doesn't exist that it doesn't add
     * @param car - transport object of car with id
     * @param employee - transport object of employee with id
     */
    @Override
    @Transactional(readOnly = false)
    public void addKeeper(CarTO car, EmployeeTO employee) {
        EmployeeEntity foundEmployee = employeeDao.findOne(employee.getId());
        CarEntity foundCar = carDao.findOne(car.getId());

        if (foundEmployee != null && foundCar != null) {
            foundEmployee.getCars().add(foundCar);
            employeeDao.update(foundEmployee);
        }
    }

    /**
     * This method adds car to database
     * Before this operation, car color and type are saved to database
     *
     * @param car - transport object withou id
     * @return transport object of car with new id
     */
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
    public ColorTO addColor(ColorTO color) {
        ColorEntity colorEntity = ColorMapper.toEntity(color);
        return ColorMapper.toTO(colorDao.save(colorEntity));
    }

    @Override
    public List<ColorTO> findAllColors() {
        return ColorMapper.map2TOs(colorDao.findAll());
    }

    /**
     * This method updates car information
     * Before save to database, related entities are get from database
     *
     * @param car tranasport object of car with id
     * @return the same transport object of car with id
     */
    @Override
    @Transactional(readOnly = false)
    public CarTO updateCar(CarTO car) {
        CarEntity carEntity = CarMapper.toEntity(car);

        carEntity.setColor(colorDao.findOne(car.getColor()));
        carEntity.setType(typeDao.findOne(car.getType()));
        List<LoanEntity> loans = new LinkedList<>();
        for (Long loanId : car.getLoans()) {
            loans.add(loanDao.findOne(loanId));
        }
        carEntity.setLoans(loans);
        return CarMapper.toTO(carDao.update(carEntity));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCar(long car_id) {
        carDao.deleteCar(car_id);
    }


    @Override
    @Transactional(readOnly = false)
    public TypeTO addType(TypeTO type) {
        TypeEntity typeEntity = TypeMapper.toEntity(type);
        return TypeMapper.toTO(typeDao.save(typeEntity));
    }

}
