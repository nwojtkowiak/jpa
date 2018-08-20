package com.capgemini.dao;

import com.capgemini.domain.ColorEntity;

public interface ColorDao extends Dao<ColorEntity, Long> {
    ColorEntity add(ColorEntity entity);
}
