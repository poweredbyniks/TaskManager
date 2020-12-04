package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {
    @NotNull
    List findAll();

    @Nullable
    Optional findOne(String name);

    boolean save(Project project);

    boolean update(Project project);

    void remove(String name);

    void removeAll();
}
