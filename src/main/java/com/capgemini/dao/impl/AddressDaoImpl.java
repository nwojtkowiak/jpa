package com.capgemini.dao.impl;

import com.capgemini.dao.AddressDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.domain.AddressEntity;
import com.capgemini.domain.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Repository
public class AddressDaoImpl extends AbstractDao<AddressEntity, Long> implements AddressDao {


    @Override
    public AddressEntity add(AddressEntity entity) {
        TypedQuery<AddressEntity> query = entityManager.createQuery(
                "select e from AddressEntity e where e.street = :street " +
                        " and e.building=:building and e.flat=:flat " +
                        " and e.post_code=:postCode and e.city=:city",AddressEntity.class);
        query.setParameter("street", entity.getStreet());
        query.setParameter("building", entity.getBuilding());
        query.setParameter("flat", entity.getFlat());
        query.setParameter("postCode", entity.getPost_code());
        query.setParameter("city", entity.getCity());


        try{
            return query.getSingleResult();
        }catch (NoResultException e) {
            return save(entity);
        }

    }

    @Override
    public void deleteAddress(long id) {
        delete(id);
    }


}
