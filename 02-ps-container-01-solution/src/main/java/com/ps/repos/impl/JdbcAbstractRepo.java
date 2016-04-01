package com.ps.repos.impl;

import com.ps.base.AbstractEntity;
import com.ps.repos.AbstractRepo;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {
    protected DataSource dataSource;

    public JdbcAbstractRepo(){
    }

    public JdbcAbstractRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(T entity) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void deleteById(Long entityId) {

    }

    @Override
    public T findById(Long entityId) {
        return null;
    }
}
