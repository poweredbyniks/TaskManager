package org.niks.repository;

import lombok.Value;
import org.niks.entity.Task;
import org.niks.entity.User;
import org.niks.service.UserService;

import java.util.*;

@Value
public class TaskRepo extends Repository<Task> {
    Map<String, Task> taskMap = new HashMap<>();
    UserService userService;
    ProjectRepo projectRepo;

    public TaskRepo(UserService userService, ProjectRepo projectRepo) {
        this.userService = userService;
        this.projectRepo = projectRepo;
    }

    private User currentUser() {
        return userService.getCurrentUser();
    }

    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == currentUser().getUserID()) {
                taskList.add(taskEntry.getValue());
            }
        }
        return taskList;
    }

    public Optional<Task> findOne(String name) {
        return Optional.ofNullable(taskMap.get(name));
    }

    public boolean save(Task task) {
        try {
            taskMap.put(task.getTaskName(), task);
            projectRepo.findOne(task.getProjectName()).get().getTaskList().add(task);

        } catch (NoSuchElementException e) {
            System.out.println("Project not found");
        }
        return true;
    }

    public boolean update(Task task) {
        return false;
    }

    public void remove(String name) {
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
