package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public final class ProjectRepository extends Serialization<Project> implements IProjectRepository {
    private final Map<String, Project> projectMap = new LinkedHashMap<>();
    private final IUserService userService;

    public ProjectRepository(IUserService userService) {
        this.userService = userService;
        try {
            projectMap.putAll(readJSON().stream().collect(Collectors.toMap(Project::getProjectName, project -> project)));
        } catch (IOException e) {
            System.out.println("No project data found");
        }
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Project> findAll() {
        @NotNull final List<Project> projectList = new ArrayList<>();
        projectMap.forEach((s, project) -> {
            if (project.getUserID() == currentUser().getUserID()) {
                projectList.add(project);
            }
        });
        return projectList;
    }

    @NotNull
    public Optional<Project> findOne(@NotNull final String name) throws NoSuchElementException {
        Optional<Project> project = Optional.empty();
        if (project.get().getUserID() == currentUser().getUserID()) {
            project = Optional.ofNullable(projectMap.get(name));
        }
        return project;
    }

    public boolean save(@NotNull final Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public boolean update(@NotNull final Project project) {
        return false;
    }

    public void remove(@NotNull final String name) {
        projectMap.forEach((s, project) -> {
            if (project.getUserID() == currentUser().getUserID()) {
                projectMap.remove(name);
            }
        });
    }

    public void removeAll() {
        projectMap.forEach((s, project) -> {
            if (project.getUserID() == currentUser().getUserID()) {
                projectMap.remove(project.getProjectName());
            }
        });
    }

    @Override
    public List<Project> readJSON() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.asList(mapper.readValue(new File(FilePath.PROJECT_FILE_PATH), Project[].class));
    }

    public void serialize() throws IOException {
        writeJSON(projectMap, FilePath.PROJECT_FILE_PATH);
    }
}
