package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.TaskSort;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;
import org.niks.repository.ITaskRepository;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public final class TaskService implements ITaskService {
    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;

    public void create(@NotNull final Task task) {
        taskRepository.save(task);
    }

    public @NotNull List<Task> list() {
        return taskList();
    }

    public @NotNull List<Task> list(@NotNull final String order) {
        final List<Task> taskList = taskList();
        taskList.sort(TaskSort.valueOf(order).getTaskComparator());
        return taskList;
    }

    public @NotNull List<Task> list(@NotNull final TaskSort order) {
        final List<Task> taskList = taskList();
        taskList.sort(order.getTaskComparator());
        return taskList;
    }

    public void remove(@NotNull final String taskToRemove) {
        taskRepository.remove(taskToRemove);
    }

    public void clear() {
        taskRepository.removeAll();
    }

    @NotNull
    public List<Task> taskSearch(@NotNull final String source) {
        final List<Task> taskList = taskList();
        final List<Task> foundTaskList = new ArrayList<>();
        for (Task task : taskList) {
            String sourceToLowerCase = source.toLowerCase();
            if (task.getTaskName().toLowerCase().contains(sourceToLowerCase) ||
                    task.getTaskDescription().toLowerCase().contains(sourceToLowerCase)) {
                foundTaskList.add(task);
            }
        }
        return foundTaskList;
    }

    @NotNull
    public List<Project> projectList() {
        return projectRepository.findAll();
    }

    @NotNull
    public List<Task> taskList() {
        return taskRepository.findAll();
    }
}
