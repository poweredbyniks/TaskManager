package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Project;
import org.example.entity.Task;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TaskRepo {
    private Map<String, Task> taskMap = new HashMap<>();

    public Map<String, Task> showAll(User user) {
        Map<String, Task> userTaskMap = new HashMap<>();
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == user.getUserID()) {
                userTaskMap.put(taskEntry.getKey(), taskEntry.getValue());
            }
        }
        return userTaskMap;
    }

    public List<Task> findAll(List<String> names) {
        List<Task> taskList = new ArrayList<>();
        for (String name : names) {
            if (names.contains(name))
                taskList.add(taskMap.get(name));
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

    public boolean update(Task task) {
        return false;
    }

    public void remove(String name, User user) {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == user.getUserID()) {
                taskMap.remove(name);
            }
        }
    }

    public void removeAll(User user) {
        for (Map.Entry<String, Task> taskEntry : taskMap.entrySet()) {
            if (taskEntry.getValue().getUserID() == user.getUserID()) {
                taskMap.remove(taskEntry.getKey());
            }
        }
    }
}
