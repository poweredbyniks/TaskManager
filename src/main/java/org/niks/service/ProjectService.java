package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.repository.IProjectRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public final class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;

    public void create(@NotNull final Project project) {
        projectRepository.save(project);
    }

    @NotNull
    public List<Project> list() {
        return projectList();
    }

    @NotNull
    public List<Project> list(@NotNull final String order) {
        final List<Project> projectList = projectList();
        projectList.sort(ProjectSort.valueOf(order).getProjectComparator());
        return projectList;
    }

    @NotNull
    public List<Project> list(@NotNull final ProjectSort order) {
        final List<Project> projectList = projectList();
        projectList.sort(order.getProjectComparator());
        return projectList;
    }

    public void remove(@NotNull final String projectToRemove) {
        projectRepository.remove(projectToRemove);
    }

    public void clear() {
        projectRepository.removeAll();
    }

    @NotNull
    public List<Project> projectSearch(@NotNull final String source) {
        final List<Project> projectList = projectList();
        return projectList
                .stream()
                .filter(project -> project.getProjectName().toLowerCase().contains(source.toLowerCase()) ||
                        project.getProjectDescription().toLowerCase().contains(source.toLowerCase()))
                .collect(Collectors.toList());
    }

    @NotNull
    public List<Project> projectList() {
        return projectRepository.findAll();
    }

    public void serialize() throws IOException {
        projectRepository.serialize();
    }
}
