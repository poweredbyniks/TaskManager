package org.niks.repository;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

@Value
public class TaskRepository implements ITaskRepository {
    Map<String, Task> taskMap = new HashMap<>();
    UserService userService;
    ProjectRepository projectRepository;

    public TaskRepository(UserService userService, ProjectRepository projectRepository) {
        this.userService = userService;
        this.projectRepository = projectRepository;
    }

    @Nullable
    private User currentUser() {
        return userService.getCurrentUser();
    }

    @NotNull
    public List<Task> findAll() {
        @NotNull List<Task> taskList = new ArrayList<>();
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskList.add(taskEntry.getValue());
            }
        }
        return taskList;
    }

    @NotNull
    public Optional<Task> findOne(@NotNull String name) {
        return Optional.ofNullable(taskMap.get(name));
    }

    public boolean save(@NotNull Task task) {
        try {
            taskMap.put(task.getTaskName(), task);
            projectRepository.findOne(task.getProjectName()).get().getTaskList().add(task);

        } catch (NoSuchElementException e) {
            System.out.println("Project not found");
        }
        return true;
    }

    public boolean update(@NotNull Task task) {
        return false;
    }

    public void remove(@NotNull String name) {
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
}
