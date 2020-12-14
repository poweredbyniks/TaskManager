package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;
import org.niks.repository.ITaskRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public final class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;

    public void create(@NotNull final Task task) {
        taskRepository.save(task);
    }

    public List<Task> list(@NotNull final String order) {
        final List<Task> taskList = taskList();
        try {
            switch (order) {
                case "start date":
                    taskList.sort(COMPARE_BY_START_DATE);
                    break;
                case "finish date":
                    taskList.sort(COMPARE_BY_FINISH_DATE);
                    break;
                case "status":
                    taskList.sort(COMPARE_BY_STATUS);
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void remove(@NotNull final String taskToRemove) {
        taskRepository.remove(taskToRemove);
    }

    public void clear() {
        taskRepository.removeAll();
    }


    public List<Task> taskSearch(@NotNull final String source) {
        final List<Task> taskList = taskList();
        final List<Task> foundTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getTaskName().toLowerCase().contains(source.toLowerCase()) ||
                    task.getTaskDescription().toLowerCase().contains(source.toLowerCase())) {
                foundTaskList.add(task);
            }
        }
        return foundTaskList;
    }

    public List<Project> projectList() {
        return projectRepository.findAll();
    }

    public List<Task> taskList() {
        return taskRepository.findAll();
    }


    public static Comparator<Task> COMPARE_BY_START_DATE = Comparator.comparing(Task::getStartDate);
    public static Comparator<Task> COMPARE_BY_FINISH_DATE = Comparator.comparing(Task::getFinishDate);
    public static Comparator<Task> COMPARE_BY_STATUS = Comparator.comparing(Task::getTaskStatus);
}
