package org.niks.service;


import org.niks.entity.Project;

public interface IProjectService {
    void create(Project project);

    void list();

    void remove(String projectToRemove);

    void clear();
}
