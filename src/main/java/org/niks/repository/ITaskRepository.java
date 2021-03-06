package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    @NotNull List<Task> findAll() throws Exception;

    @NotNull Optional<Task> findOne(@NotNull final String name);

    void save(@NotNull final Task entity);

    void update(@NotNull final Task entity);

    void remove(@NotNull final String name);

    void removeAll();

}
