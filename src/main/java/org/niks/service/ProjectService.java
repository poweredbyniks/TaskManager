package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    @Autowired
    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

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

    public Project findExactMatch(@NotNull final String name) {
        Project project = null;
        if (projectRepository.findOne(name).isPresent()) {
            project = projectRepository.findOne(name).get();
        }
        return project;
    }

    public void update(@NotNull final Project project) {

        projectRepository.update(project);
    }

    @NotNull
    public List<Project> projectList() {
        return projectRepository.findAll();
    }

//    public void serialize() throws IOException {
//        projectRepository.serialize();
//    }

}
