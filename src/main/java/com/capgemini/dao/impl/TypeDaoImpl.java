package com.capgemini.dao.impl;

import com.capgemini.dao.ColorDao;
import com.capgemini.dao.TypeDao;
import com.capgemini.domain.ColorEntity;
import com.capgemini.domain.TypeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;


@Repository
public class TypeDaoImpl extends AbstractDao<TypeEntity, Long> implements TypeDao {

    public TypeEntity add(TypeEntity entity){
        TypedQuery<TypeEntity> query = entityManager.createQuery("select e from TypeEntity e where e.name = :name",TypeEntity.class);
        query.setParameter("name", entity.getName());

        if(query.getSingleResult() != null){
            return query.getSingleResult();
        }
        return save(entity);

    }
}
