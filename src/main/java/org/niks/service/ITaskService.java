package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

public interface ITaskService {
    void create(@NotNull final Task task);

    void list();

    void remove(@NotNull final String taskToRemove);

    void clear();
}
