package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;

import java.io.BufferedReader;

public interface IProjectService {
    void create(@NotNull final Project project);

    void list(@NotNull final BufferedReader reader);

    void remove(@NotNull final String projectToRemove);

    void clear();

    void projectSearch(@NotNull final String source);
}
