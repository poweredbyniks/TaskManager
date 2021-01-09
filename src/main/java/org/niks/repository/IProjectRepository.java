package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    @NotNull List<Project> findAll();

    @NotNull Optional<Project> findOne(@NotNull final String name);

    boolean save(@NotNull final Project entity);

    boolean update(@NotNull final Project entity);

    void remove(@NotNull final String name);

    void removeAll();

    void serialize() throws IOException;
}
