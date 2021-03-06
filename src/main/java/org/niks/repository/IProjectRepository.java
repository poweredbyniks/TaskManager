package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    @NotNull List<Project> findAll() throws Exception;

    @NotNull Optional<Project> findOne(@NotNull final String name);

    void save(@NotNull final Project entity);

    void update(@NotNull final Project entity);

    void remove(@NotNull final String name);

    void removeAll();

//    void serialize() throws IOException;
}
