package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {

    void save(@NotNull final Project entity);

    @NotNull List<Project> findAll();

    @NotNull Optional<Project> findOne(@NotNull final String name);

    @NotNull List<Project> projectSearch(@NotNull final String word);

    @NotNull Project findByID(final long projectID);

    void update(@NotNull final Project entity);

    void remove(@NotNull final String name);

    void removeAll();

}
