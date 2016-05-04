package com.ps.repos.impl;

import com.ps.base.AbstractEntity;
import com.ps.repos.AbstractRepo;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {
    protected String findByIdQuery="";

    protected JdbcTemplate jdbcTemplate;

    public JdbcAbstractRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
