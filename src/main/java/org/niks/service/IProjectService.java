package org.niks.service;


import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;

public interface IProjectService<T> {
    void create(@NotNull final T project);

    void list(@NotNull final BufferedReader reader) throws IOException;

    void remove(@NotNull final String projectToRemove);

    void clear();
}
