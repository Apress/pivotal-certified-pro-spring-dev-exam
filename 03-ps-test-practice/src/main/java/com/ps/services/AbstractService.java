package com.ps.services;

import com.ps.base.AbstractEntity;
import com.ps.repos.AbstractRepo;
import com.ps.repos.PetRepo;

/**
 * Created by iuliana.cosmina on 3/8/16.
 */
public interface AbstractService<T> {
    void save(T entity);

    T findById(Long entityId);

    void delete(T entity);

    void deleteById(Long entityId);
}
