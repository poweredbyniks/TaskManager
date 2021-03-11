package org.niks.service;


import org.jetbrains.annotations.NotNull;
import org.niks.enums.ProjectSort;
import org.niks.entity.Project;

import java.sql.SQLException;
import java.util.List;

public interface IProjectService {
    void create(@NotNull final Project project) throws SQLException;

    @NotNull
    List<Project> list() throws SQLException;

    @NotNull
    List<Project> list(@NotNull final String order) throws SQLException;

    @NotNull
    List<Project> list(@NotNull final ProjectSort order) throws SQLException;

    void remove(@NotNull final String projectToRemove) throws SQLException;

    void clear();

    @NotNull
    List<Project> projectSearch(@NotNull final String source) throws SQLException;

    Project findExactMatch(@NotNull final String name) throws SQLException;

    void update(@NotNull final Project project) throws SQLException;

}
