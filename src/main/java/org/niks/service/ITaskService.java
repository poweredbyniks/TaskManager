package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.util.List;

public interface ITaskService {
    void create(@NotNull final Task task);

    @NotNull
    List<Task> list();

    @NotNull
    List<Task> list(@NotNull final String order);

    @NotNull List<Task> list(final long projectID);

    void remove(@NotNull final String taskToRemove);

    void clear();

    @NotNull
    List<Task> taskSearch(@NotNull final String source);

    Task findExactMatch(@NotNull final String name);

    void update(@NotNull final Task task);

}
