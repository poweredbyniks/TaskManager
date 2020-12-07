package org.niks.repository;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.util.*;

@AllArgsConstructor
public final class ProjectRepository implements IProjectRepository<Project> {
    private final Map<String, Project> projectMap = new LinkedHashMap<>();
    private final IUserService<User> iUserService;

    @Nullable
    private User currentUser() {
        return iUserService.getCurrentUser();
    }

    @NotNull
    public List findAll() {
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
}
