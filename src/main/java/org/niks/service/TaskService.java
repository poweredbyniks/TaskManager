package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.enums.TaskSort;
import org.niks.entity.Task;
import org.niks.repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;


    @Autowired
    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

    public @NotNull List<Task> list(final long projectID) {
        return taskRepository.findAllTasks(projectID);
    }

    public void remove(@NotNull final String taskToRemove) {
        taskRepository.remove(taskToRemove);
    }

    public void clear() {
        taskRepository.removeAll();
    }

    @NotNull
    public List<Task> taskSearch(@NotNull final String word) {
        return taskRepository.taskSearch(word);
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

    public void update(@NotNull final Task task) {
        taskRepository.update(task);
    }
}
