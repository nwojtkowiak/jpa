package com.capgemini.dao.impl;

import com.capgemini.dao.OfficeDao;
import com.capgemini.domain.OfficeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Repository
public class OfficeDaoImpl extends AbstractDao<OfficeEntity, Long> implements OfficeDao {

    
    @Override
    public OfficeEntity add(OfficeEntity entity) {
        TypedQuery<OfficeEntity> query = entityManager.createQuery(
                "select e from OfficeEntity e " +
                        "where e.name = :name", OfficeEntity.class);
        query.setParameter("name", entity.getName());

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return save(entity);
        }
    }


}
