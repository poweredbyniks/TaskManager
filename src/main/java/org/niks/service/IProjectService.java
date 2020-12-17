package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

import java.util.List;

public interface IProjectService {
    void create(@NotNull final Project project);

    List<Project> list();

    List<Project> list(String order);

    void remove(@NotNull final String projectToRemove);

    void clear();

    List<Project> projectSearch(@NotNull final String source);
}
