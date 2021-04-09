package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.enums.ProjectSort;
import org.niks.entity.Project;
import org.niks.exception.RepositoryException;
import org.niks.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ProjectService implements IProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
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
    public Project findByID(@NotNull  final Long projectId) {
                return projectRepository.findById(projectId).orElseThrow(() ->
                        new RepositoryException("findByID", "ProjectRepository"));
    }

    public void remove(@NotNull final Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public void clear() {
        projectRepository.deleteAll();
    }

    @NotNull
    public List<Project> projectSearch(@NotNull final String word) {
        return projectRepository.findAllByProjectNameContainingAndAndProjectDescriptionContaining(word);
    }

    public void update(@NotNull final Project project) {
        projectRepository.update(project.getProjectName(), project.getProjectDescription(), project.getStartDate(),
                project.getFinishDate(), project.getProjectStatus(), project.getProjectID());
    }

    @NotNull
    public List<Project> projectList() {
        return projectRepository.findAll();
    }
}
