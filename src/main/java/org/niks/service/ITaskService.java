package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;

import java.io.BufferedReader;

public interface ITaskService {
    void create(@NotNull final Task task);

    void list(@NotNull final BufferedReader reader);

    void remove(@NotNull final String taskToRemove);

    void clear();

    void taskSearch(@NotNull final String source);
}
