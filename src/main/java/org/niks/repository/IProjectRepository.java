package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    @NotNull List <Project> findAll();

    @NotNull Optional <Project> findOne(@NotNull String name);

    boolean save(@NotNull Project entity);

    boolean update(@NotNull Project entity);

    void remove(@NotNull String name);

    void removeAll();
}
