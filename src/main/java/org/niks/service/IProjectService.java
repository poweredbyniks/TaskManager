package org.niks.service;


import org.jetbrains.annotations.NotNull;

public interface IProjectService<T> {
    void create(@NotNull final T project);

    void list();

    void remove(@NotNull final String projectToRemove);

    void clear();
}
