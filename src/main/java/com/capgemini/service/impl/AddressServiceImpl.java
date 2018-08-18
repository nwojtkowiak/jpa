package com.capgemini.service.impl;

import com.capgemini.dao.AddressDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.mappers.AddressMapper;
import com.capgemini.service.AddressService;
import com.capgemini.types.AddressTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Override
    public AddressTO addAddress(AddressTO address) {
        AddressEntity addressEntity = AddressMapper.toEntity(address);
        return AddressMapper.toTO(addressDao.add(addressEntity));
    }
}
