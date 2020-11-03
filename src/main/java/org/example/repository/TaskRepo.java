package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class TaskRepo {
    private static Map<String, Task> taskMap = new HashMap<>();

    public Map<String, Task> showAll() {
        return taskMap;
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
        taskMap.put(task.getTaskName(), new Task(task.getTaskID(), task.getTaskName(),
                task.getProjectName(), task.getTaskDescription(), task.getStartDate(), task.getFinishDate()));
        return true;
    }

    public void update() {

    }

    public void remove(String name) {
        taskMap.remove(name);
    }

    public void removeAll() {
        taskMap.clear();
    }
}
