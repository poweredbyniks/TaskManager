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
    private final Map<String, Project> projectMap = readJSON().stream().collect(Collectors.toMap(Project::getProjectName, project -> project));
    private final IUserService userService;

    public ProjectRepository(IUserService userService) {
        this.userService = userService;
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
    public Optional<Project> findOne(@NotNull final String name) {
        return Optional.ofNullable(projectMap.get(name));
    }

    public boolean save(@NotNull final Project project) {
        projectMap.put(project.getProjectName(), project);
        return true;
    }

    public boolean update(@NotNull final Project project) {
        return false;
    }

    public void remove(@NotNull final String name) {
        if (projectMap.get(name).getUserID() == currentUser().getUserID()) {
            projectMap.remove(name);
        }
    }

    public void removeAll() {
        projectMap.entrySet().removeIf(stringProjectEntry ->
                stringProjectEntry.getValue().getUserID() == currentUser().getUserID());
    }

    @Override
    public List<Project> readJSON() {
        List<Project> list = new ArrayList<>();
        try {
            final ObjectMapper mapper = new ObjectMapper();
            list = Arrays.asList(mapper.readValue(new File(FilePath.PROJECT_FILE_PATH), Project[].class));
        } catch (IOException e) {
            System.out.println("No project data found");
        }
        return list;
    }

    public void serialize() throws IOException {
        writeJSON(projectMap, FilePath.PROJECT_FILE_PATH);
    }
}
