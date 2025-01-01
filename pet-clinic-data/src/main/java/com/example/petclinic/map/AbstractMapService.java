package com.example.petclinic.map;

import com.example.petclinic.model.BaseEntity;
import com.example.petclinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Number> implements CrudService<T, ID> {

    private final Map<Long, T> map = new HashMap<>();

    @Override
    public T findById(ID id) {
        return map.get(id);
    }

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public T save(T t) {
        if (t != null) {
            if (t.getId() == null) {
                t.setId(getNextId());
            }
            map.put(t.getId(), t);
        } else {
            throw new NoSuchElementException("Object cannot be null");
        }
        return t;
    }

    @Override
    public void delete(T t) {
        map.entrySet().removeIf(e -> e.getValue().equals(t));
    }

    @Override
    public void deleteById(ID id) {
        map.remove(id);
    }

    private Long getNextId() {
        if (map.isEmpty()) {
            return 1L;
        }
        return Collections.max(map.keySet()) + 1;
    }
}
