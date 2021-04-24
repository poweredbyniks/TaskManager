package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

import java.util.List;

public interface IProjectService {
    void create(@NotNull final Project project);

    @NotNull
    List<Project> list();

    @NotNull
    List<Project> list(final String order);

    @NotNull
    Project findByID(final Long projectID);

    void remove(@NotNull final Long projectId);

    void clear();

    @NotNull
    List<Project> projectSearch(@NotNull final String source);

    void update(@NotNull final Project project);

}
