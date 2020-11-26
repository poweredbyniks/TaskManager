package org.niks.repository;

import java.util.List;
import java.util.Optional;

public abstract class Repository<T> {

    public abstract List<T> findAll();

    public abstract Optional<T> findOne(String name);

    public abstract boolean save(T entity);

    public abstract boolean update(T entity);

    public abstract void remove(String name);

    public abstract void removeAll();
}
