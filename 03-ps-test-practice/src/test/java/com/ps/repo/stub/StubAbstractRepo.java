package com.ps.repo.stub;

import com.ps.base.AbstractEntity;
import com.ps.repos.AbstractRepo;
import com.ps.repos.NotFoundException;

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
    public void delete(T entity) throws NotFoundException {
        if(records.containsKey(entity.getId())) {
            records.remove(entity.getId());
        } else {
            throw new NotFoundException("Entity with id "
                    + entity.getId() + " could not be deleted because it does not exists");
        }
    }

    @Override
    public void deleteById(Long entityId) throws NotFoundException {
        if(records.containsKey(entityId)) {
            records.remove(entityId);
        } else {
            throw new NotFoundException("Entity with id "
                    + entityId + " could not be deleted because it does not exists");
        }
    }

    @Override
    public T findById(Long entityId) {
        return records.get(entityId);
    }

}
