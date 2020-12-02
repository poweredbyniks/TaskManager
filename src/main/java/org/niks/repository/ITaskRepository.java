package org.niks.repository;

import org.niks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    List findAll();

    Optional findOne(String name);

    boolean save(Task task);

    boolean update(Task task);

    void remove(String name);

    void removeAll();
}
