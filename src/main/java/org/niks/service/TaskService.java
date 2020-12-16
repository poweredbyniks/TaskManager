package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.TaskSort;
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

    public List<Task> list() {
        return taskList();
    }

    public List<Task> list(@NotNull final String order) {
        Comparator<Task> taskComparator;
        final List<Task> taskList = taskList();
        for (TaskSort taskSort : TaskSort.values()) {
            if (taskSort.getOrder().equals(order)) {
                taskComparator = taskSort.getTaskComparator();
                taskList.sort(taskComparator);
            }
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
}
