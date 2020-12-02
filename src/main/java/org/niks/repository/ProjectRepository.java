package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

public interface ProjectRepository {
    Map<String, Project> projectMap = new HashMap<>();


    @Nullable
    static User currentUser() {
        return UserService.getCurrentUser();
    }

    @NotNull
    public static List<Project> findAll() {
        List<Project> projectList = new ArrayList<>();
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectList.add(projectEntry.getValue());
            }
        }
        return projectList;
    }

    @NotNull
    public static Optional<Project> findOne(String name) {
        Project project = null;
        if (projectMap.get(name).getUserID() == currentUser().getUserID()) {
            project = projectMap.get(name);
        }
        return Optional.ofNullable(project);
    }

    public static boolean save(Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public static boolean update(Project project) {
        return false;
    }

    public static void remove(String name) {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectMap.remove(name);
            }
        }
    }

    public static void removeAll() {
        for (Map.Entry<String, Project> projectEntry : projectMap.entrySet()) {
            if (projectEntry.getValue().getUserID() == currentUser().getUserID()) {
                projectMap.remove(projectEntry.getKey());
            }
        }
    }
}
