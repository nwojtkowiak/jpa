package com.capgemini.dao.impl;

import com.capgemini.dao.ColorDao;
import com.capgemini.domain.ColorEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;


@Repository
public class ColorDaoImpl extends AbstractDao<ColorEntity, Long> implements ColorDao {


    @Override
    public ColorEntity add(ColorEntity entity) {
        TypedQuery<ColorEntity> query = entityManager.createQuery("select e from ColorEntity e where e.name = :name",ColorEntity.class);
        query.setParameter("name", entity.getName());

        if(query.getSingleResult() != null){
            return query.getSingleResult();
        }
        return save(entity);
    }
}
