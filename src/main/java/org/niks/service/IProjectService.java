package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

public interface IProjectService <Project> {
    void create(@NotNull final Project project);

    void list();

    void remove(@NotNull final String projectToRemove);

    void clear();
}
