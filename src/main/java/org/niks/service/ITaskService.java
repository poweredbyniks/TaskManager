package org.niks.service;

import org.jetbrains.annotations.NotNull;

public interface ITaskService <T> {
    void create(@NotNull final T task);

    void list();

    void remove(@NotNull final String taskToRemove);

    void clear();
}
