package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository <T> {
    @NotNull
    List <T> findAll();

    @NotNull
     Optional<T> findOne(@NotNull String name);

    boolean save(@NotNull Task entity);

    boolean update(@NotNull Task entity);

    void remove(@NotNull String name);

    void removeAll();
}
