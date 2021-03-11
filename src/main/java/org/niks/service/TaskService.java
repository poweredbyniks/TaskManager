package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.enums.TaskSort;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.repository.IProjectRepository;
import org.niks.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;
    private final IProjectRepository projectRepository;


    @Autowired
    public TaskService(ITaskRepository taskRepository, IProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

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
        return taskList
                .stream()
                .filter(task -> task.getTaskName().toLowerCase().contains(source.toLowerCase()) ||
                        task.getTaskDescription().toLowerCase().contains(source.toLowerCase()))
                .collect(Collectors.toList());
    }

    @NotNull
    public List<Task> taskList() {
        return taskRepository.findAll();
    }

    public Task findExactMatch(@NotNull final String name) {
        Task task = null;
        if (taskRepository.findOne(name).isPresent()) {
            task = taskRepository.findOne(name).get();
        }
        return task;
    }

    @NotNull
    public List<Project> projectList() throws SQLException {
        return projectRepository.findAll();
    }

    public void update(@NotNull final Task task) {
        taskRepository.update(task);
    }
}
