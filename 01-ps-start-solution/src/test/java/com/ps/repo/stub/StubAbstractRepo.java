package com.ps.repo.stub;

import com.ps.base.AbstractEntity;
import com.ps.repos.AbstractRepo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 3/9/16.
 */
public abstract class StubAbstractRepo<T extends AbstractEntity> implements AbstractRepo<T> {

    protected Map<Long, T> records = new HashMap<>();

    @Override
    public void save(T entity) {
        if (entity.getId() == null) {
            Long id = (long) records.size() + 1;
            entity.setId(id);
        }
        records.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        records.remove(entity.getId());
    }

    @Override
    public void deleteById(Long entityId) {
        records.remove(entityId);
    }

    @Override
    public T findById(Long entityId) {
        return records.get(entityId);
    }
}
