package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    @NotNull List<Task> findAll();

    @NotNull Optional<Task> findOne(@NotNull final String name);

    boolean save(@NotNull Task entity);

    boolean update(@NotNull Task entity);

    void remove(@NotNull String name);

    void removeAll();
}
