package com.capgemini.dao.impl;

import com.capgemini.dao.PositionDao;
import com.capgemini.domain.PositionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Repository
public class PositionDaoImpl extends AbstractDao<PositionEntity, Long> implements PositionDao {

    @Override
    public PositionEntity add(PositionEntity entity){
        TypedQuery<PositionEntity> query = entityManager.createQuery(
                "select e from PositionEntity e where e.name = :name",PositionEntity.class);
        query.setParameter("name", entity.getName());

        try{
            return query.getSingleResult();
        }catch (NoResultException e) {
            return save(entity);
        }

    }

}
