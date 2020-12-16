package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.repository.IProjectRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public final class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public void create(@NotNull final Project project) {
        {
            projectRepository.save(project);
        }
    }

    public List<Project> list() {
        return projectList();
    }

    public List<Project> list(@NotNull final String order) {
        Comparator<Project> projectComparator;
        final List<Project> projectList = projectList();
        for (ProjectSort projectSort : ProjectSort.values()) {
            if (projectSort.getOrder().equals(order)) {
                projectComparator = projectSort.getProjectComparator();
                projectList.sort(projectComparator);
            }
        }
        return projectList;
    }

    public void remove(@NotNull final String projectToRemove) {
        projectRepository.remove(projectToRemove);
    }

    public void clear() {
        projectRepository.removeAll();
    }

    public List<Project> projectSearch(@NotNull final String source) {
        final List<Project> projectList = projectList();
        final List<Project> foundProjectList = new ArrayList<>();
        for (Project project : projectList) {
            if (project.getProjectName().toLowerCase().contains(source.toLowerCase()) ||
                    project.getProjectDescription().toLowerCase().contains(source.toLowerCase())) {
                foundProjectList.add(project);
            }
        }
        return foundProjectList;
    }

    public List<Project> projectList() {
        return projectRepository.findAll();
    }
}
