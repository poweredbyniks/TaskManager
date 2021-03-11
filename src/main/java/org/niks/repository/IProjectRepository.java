package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    @NotNull List<Project> findAll() throws SQLException;

    @NotNull Optional<Project> findOne(@NotNull final String name) throws SQLException;

    void save(@NotNull final Project entity) throws SQLException;

    void update(@NotNull final Project entity) throws SQLException;

    void remove(@NotNull final String name) throws SQLException;

    void removeAll();

}
