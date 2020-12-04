package org.niks.repository;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

@Value
public class ProjectRepository implements IProjectRepository {
    Map<String, Project> projectMap = new HashMap<>();
    UserService userService;

    public ProjectRepository(UserService userService) {
        this.userService = userService;
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List findAll() {
        @NotNull
        final List<Project> projectList = new ArrayList<>();
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectList.add(projectEntry.getValue());
            }
        }
        return projectList;
    }

    @NotNull
    public Optional<Project> findOne(@NotNull String name) {
        @Nullable Project project = null;
        if (projectMap.get(name).getUserID() == currentUser().getUserID()) {
            project = projectMap.get(name);
        }
        return Optional.ofNullable(project);
    }

    public boolean save(Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public boolean update(Project project) {
        return false;
    }

    public void remove(@NotNull String name) {
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
}
