package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
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

    public List<Project> list(@NotNull final String order) {
        final List<Project> projectList = projectList();
        try {
            switch (order) {
                case "start date":
                    projectList.sort(COMPARE_BY_START_DATE);
                    break;
                case "finish date":
                    projectList.sort(COMPARE_BY_FINISH_DATE);
                    break;
                case "status":
                    projectList.sort(COMPARE_BY_STATUS);
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
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

    public static Comparator<Project> COMPARE_BY_START_DATE = Comparator.comparing(Project::getStartDate);
    public static Comparator<Project> COMPARE_BY_FINISH_DATE = Comparator.comparing(Project::getFinishDate);
    public static Comparator<Project> COMPARE_BY_STATUS = Comparator.comparing(Project::getProjectStatus);
}
