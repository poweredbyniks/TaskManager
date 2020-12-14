package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;

import java.io.BufferedReader;
import java.util.List;

public interface IProjectService {
    void create(@NotNull final Project project);

    List<Project> list(@NotNull final String order);

    void remove(@NotNull final String projectToRemove);

    void clear();

    List<Project> projectSearch(@NotNull final String source);
}
