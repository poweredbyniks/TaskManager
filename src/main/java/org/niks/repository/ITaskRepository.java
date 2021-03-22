package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    @NotNull List<Task> findAll();

    List<Task> findAllTasks(long projectID);

    @NotNull Optional<Task> findOne(@NotNull final String name);

    @NotNull List<Task> taskSearch(@NotNull final String word);

    void save(@NotNull final Task entity);

    void update(@NotNull final Task entity);

    void remove(@NotNull final String name);

    void removeAll();
}
