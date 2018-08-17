package com.capgemini.dao;

import com.capgemini.domain.ColorEntity;
import com.capgemini.domain.TypeEntity;

public interface TypeDao extends Dao<TypeEntity, Long> {
    TypeEntity add(TypeEntity entity);
}
