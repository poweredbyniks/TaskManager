package org.niks.repository;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

public interface TaskRepository {
    Map<String, Task> taskMap = new HashMap<>();


    @Nullable
    static User currentUser() {
        return UserService.getCurrentUser();
    }

    @NotNull
    public static List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskList.add(taskEntry.getValue());
            }
        }
        return taskList;
    }

    @NotNull
    public static Optional<Task> findOne(String name) {
        return Optional.ofNullable(taskMap.get(name));
    }

    public static boolean save(Task task) {
        try {
            taskMap.put(task.getTaskName(), task);
            ProjectRepository.findOne(task.getProjectName()).get().getTaskList().add(task);

        } catch (NoSuchElementException e) {
            System.out.println("Project not found");
        }
        return true;
    }

    public static boolean update(Task task) {
        return false;
    }

    public static void remove(String name) {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskMap.remove(name);
            }
        }
    }

    public static void removeAll() {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskMap.remove(taskEntry.getKey());
            }
        }
    }
}
