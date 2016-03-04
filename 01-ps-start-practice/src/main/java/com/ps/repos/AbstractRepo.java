package com.ps.repos;

import com.ps.ents.Request;

/**
 * Created by iuliana.cosmina on 3/1/16.
 */
public interface AbstractRepo<T> {

    void save(T entity);

    void delete(T entity);

    void deleteById(Long entityId);

    T findById(Long entityId);
}
