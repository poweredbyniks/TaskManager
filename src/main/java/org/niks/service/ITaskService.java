package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;

import java.io.BufferedReader;
import java.util.List;

public interface ITaskService {
    void create(@NotNull final Task task);

    List<Task> list(@NotNull final String order);

    void remove(@NotNull final String taskToRemove);

    void clear();

    List<Task> taskSearch(@NotNull final String source);

    List<Project> projectList();
}
