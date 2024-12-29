package com.example.petclinic.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID>  {

    private final Map<ID, T> map = new HashMap<>();

    T findById(ID id) {
        return map.get(id);
    };

    Set<T> findAll() {
        return new HashSet<>(map.values());
    };

    T save(ID id, T t) {
        return map.put(id, t);
    };

    void delete(T t) {
        map.entrySet().removeIf(e -> e.getValue().equals(t));
    };

    void deleteById(ID id) {
        map.remove(id);
    };

}
