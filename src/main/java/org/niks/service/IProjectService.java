package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;

import java.io.IOException;
import java.util.List;

public interface IProjectService {
    void create(@NotNull final String projectName, String projectDescription, String startDate,
                String finishDate);

    @NotNull
    List<Project> list();

    @NotNull
    List<Project> list(@NotNull final String order);

    @NotNull
    List<Project> list(@NotNull final ProjectSort order);

    void remove(@NotNull final String projectToRemove);

    void clear();

    @NotNull
    List<Project> projectSearch(@NotNull final String source);

    Project findExactMatch(@NotNull final String name);

    void update(@NotNull final Project project);

    void serialize() throws IOException;
}
