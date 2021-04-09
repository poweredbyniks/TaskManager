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

    @NotNull
    Task findByID(@NotNull final Long taskID);

    void remove(@NotNull final Long taskID);

    void clear();

    @NotNull
    List<Task> taskSearch(@NotNull final String source);

    void update(@NotNull final Task task);

}
