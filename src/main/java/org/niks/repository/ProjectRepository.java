package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.IOException;
import java.util.*;


public final class ProjectRepository implements IProjectRepository, ISerialization<Project> {
    private final Map<String, Project> projectMap = new LinkedHashMap<>();
    private final IUserService userService;

    private final String filePath = "/Users/elupokniks/Desktop/ProjectsData.json";

    public ProjectRepository(IUserService userService) throws IOException {
        this.userService = userService;
        Project[] projects = readProjectJSON(filePath);
        for (Project project : projects) {
            projectMap.put(project.getProjectName(), project);
        }
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();

    }

    @NotNull
    public List<Project> findAll() {
        @NotNull final List<Project> projectList = new ArrayList<>();
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectList.add(projectEntry.getValue());
            }
        }
        return projectList;
    }

    @NotNull
    public Optional<Project> findOne(@NotNull final String name) {
        Project project = null;
        if (projectMap.get(name).getUserID() == currentUser().getUserID()) {
            project = projectMap.get(name);
        }
        return Optional.ofNullable(project);
    }

    public boolean save(@NotNull final Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public boolean update(@NotNull final Project project) {
        return false;
    }

    public void remove(@NotNull final String name) {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectMap.remove(name);
            }
        }
    }

    public void removeAll() {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectMap.remove(projectEntry.getKey());
            }
        }
    }

    public void serialize() throws IOException {
        writeJSON(projectMap, filePath);
    }
}
