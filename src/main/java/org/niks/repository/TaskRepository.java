package org.niks.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public final class TaskRepository extends Serialization<Task> implements ITaskRepository {

    private final IUserService userService;
    private final IProjectRepository projectRepository;

    private final Map<String, Task> taskMap = readJSON().stream().collect(Collectors.toMap(Task::getTaskName, task -> task));

    public TaskRepository(IUserService userService, IProjectRepository projectRepository) {
        this.userService = userService;
        this.projectRepository = projectRepository;
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Task> findAll() {
        final List<Task> taskList = new ArrayList<>();
        taskMap.forEach((s, task) -> {
            if (task.getUserID() == currentUser().getUserID()) {
                taskList.add(task);
            }
        });
        return taskList;

    }

    @NotNull
    public Optional<Task> findOne(@NotNull final String name) {
        return Optional.ofNullable(taskMap.get(name));
    }

    public boolean save(@NotNull final Task task) {
        taskMap.put(task.getTaskName(), task);
        projectRepository.findOne(task.getProjectName()).get().getTaskList().add(task);
        return true;
    }

    public boolean update(@NotNull final Task task) {
        return false;
    }

    public void remove(@NotNull final String name) {
        if (taskMap.get(name).getUserID() == currentUser().getUserID()) {
            taskMap.remove(name);
        }
    }

    public void removeAll() {
        taskMap.entrySet().removeIf(stringTaskEntry ->
                stringTaskEntry.getValue().getUserID() == currentUser().getUserID());
    }

    @Override
    public List<Task> readJSON() {
        List<Task> list = new ArrayList<>();
        try {
            @NotNull final ObjectMapper mapper = new ObjectMapper();
            list = Arrays.asList(mapper.readValue(new File(FilePath.TASK_FILE_PATH), Task[].class));
        } catch (IOException e) {
            System.out.println("No task data found");
        }
        return list;
    }

    public void serialize() throws IOException {
        writeJSON(taskMap, FilePath.TASK_FILE_PATH);
    }
}
