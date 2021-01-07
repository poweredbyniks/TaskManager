package org.niks.repository;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.io.IOException;
import java.util.*;


public final class TaskRepository implements ITaskRepository, ISerialization<Task> {

    private final IUserService userService;
    private final IProjectRepository projectRepository;

    private final Map<String, Task> taskMap = new HashMap<>();
    private final String filePath = "/Users/elupokniks/Desktop/TasksData.json";

    public TaskRepository(IUserService userService, IProjectRepository projectRepository) throws IOException {
        this.userService = userService;
        this.projectRepository = projectRepository;


        final Task[] tasks = readTaskJSON(filePath);
        for (Task task : tasks) {
            taskMap.put(task.getTaskName(), task);
        }
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Task> findAll() {
        final List<Task> taskList = new ArrayList<>();
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskList.add(taskEntry.getValue());
            }
        }
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
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskMap.remove(name);
            }
        }
    }

    public void removeAll() {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskMap.remove(taskEntry.getKey());
            }
        }
    }

    public void serialize() throws IOException {
        writeJSON(taskMap, filePath);
    }
}
