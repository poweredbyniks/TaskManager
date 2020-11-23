package org.niks.repository;

import org.niks.entity.Task;
import org.niks.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepo {
    private Map<String, Task> taskMap = new HashMap<>();
    private UserService userService;

    public TaskRepo(UserService userService) {
        this.userService = userService;
    }


    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == userService.getCurrentUser().getUserID()) {
                taskList.add(taskEntry.getValue());
            }
        }
        return taskList;
    }

    public Task findOne(String name) {
        return taskMap.get(name);
    }

    public boolean save(Task task) {
        taskMap.put(task.getTaskName(), task);
        return true;
    }

    public boolean update() {
        return false;
    }

    public void remove(String name) {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == userService.getCurrentUser().getUserID()) {
                taskMap.remove(name);
            }
        }
    }

    public void removeAll() {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == userService.getCurrentUser().getUserID()) {
                taskMap.remove(taskEntry.getKey());
            }
        }
    }
}
