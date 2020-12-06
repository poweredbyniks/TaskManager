package org.niks.repository;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.IUserService;

import java.util.*;

@AllArgsConstructor
public final class TaskRepository implements ITaskRepository<Task> {

    private final IUserService<User> iUserService;
    private final IProjectRepository<Project> iProjectRepository;

    private final Map<String, Task> taskMap = new HashMap<>();

    @Nullable
    private User currentUser() {
        return iUserService.getCurrentUser();
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
        try {
            taskMap.put(task.getTaskName(), task);
            iProjectRepository.findOne(task.getProjectName()).get().getTaskList().add(task);

        } catch (NoSuchElementException e) {
            System.out.println("Project not found");
        }
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
}
