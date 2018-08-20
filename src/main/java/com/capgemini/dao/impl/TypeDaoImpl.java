package com.capgemini.dao.impl;

import com.capgemini.dao.TypeDao;
import com.capgemini.domain.TypeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


@Repository
public class TypeDaoImpl extends AbstractDao<TypeEntity, Long> implements TypeDao {

    public TypeEntity add(TypeEntity entity) {
        TypedQuery<TypeEntity> query = entityManager.createQuery(
                "select e from TypeEntity e where e.name = :name", TypeEntity.class);
        query.setParameter("name", entity.getName());

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return save(entity);
        }

    }
}
