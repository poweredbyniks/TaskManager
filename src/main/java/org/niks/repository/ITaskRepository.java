package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    @NotNull List<Task> findAll();

    @NotNull Optional<Task> findOne(@NotNull final String name);

    boolean save(@NotNull final Task entity);

    boolean update(@NotNull final Task entity);

    void remove(@NotNull final String name);

    void removeAll();

    void serialize() throws IOException;
}
