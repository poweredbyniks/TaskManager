package org.niks.repository;

import org.niks.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository {

    List findAll();

    Optional findOne(String name);

    boolean save(Project project);

    boolean update(Project project);

    void remove(String name);

    void removeAll();
}
