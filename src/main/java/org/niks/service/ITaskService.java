package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.TaskSort;
import org.niks.entity.Project;
import org.niks.entity.Task;

import java.io.IOException;
import java.util.List;

public interface ITaskService {
    void create(@NotNull final Task task);

    @NotNull
    List<Task> list();

    @NotNull
    List<Task> list(@NotNull final TaskSort order);

    @NotNull
    List<Task> list(@NotNull final String order);

    void remove(@NotNull final String taskToRemove);

    void clear();

    @NotNull
    List<Task> taskSearch(@NotNull final String source);

    @NotNull
    List<Project> projectList();

    public Task findExactMatch(@NotNull final String name);

    void update(@NotNull final Project project);

    void serialize() throws IOException;
}
