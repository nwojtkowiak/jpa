package com.capgemini.dao;

import com.capgemini.domain.AddressEntity;


public interface AddressDao extends Dao<AddressEntity, Long> {

    AddressEntity add(AddressEntity entity);

	void deleteAddress(long id);



}
