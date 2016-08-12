package com.ps.repos;

import com.ps.base.AbstractEntity;

/**
 * Created by iuliana.cosmina on 3/1/16.
 */
public interface AbstractRepo<T extends AbstractEntity> {

    void save(T entity);

    void delete(T entity) throws NotFoundException;

    void deleteById(Long entityId) throws NotFoundException;

    T findById(Long entityId);
}
