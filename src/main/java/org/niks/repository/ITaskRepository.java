package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    @NotNull
    List findAll();

    @Nullable
    Optional findOne(String name);

    boolean save(Task task);

    boolean update(Task task);

    void remove(String name);

    void removeAll();
}
