package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

import java.util.List;

public interface IProjectService {
    void create(@NotNull final Project project);

    @NotNull
    List<Project> list();

    @NotNull
    List<Project> list(@NotNull final String order);

    @NotNull
    Project findByID(final long projectID);

    void remove(@NotNull final String projectToRemove);

    void clear();

    @NotNull
    List<Project> projectSearch(@NotNull final String source);

    Project findExactMatch(@NotNull final String name);

    void update(@NotNull final Project project);

}
