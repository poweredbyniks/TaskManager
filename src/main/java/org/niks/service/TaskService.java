package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.enums.TaskSort;
import org.niks.entity.Task;
import org.niks.exception.RepositoryException;
import org.niks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(@NotNull final Task task) {
        taskRepository.save(task);
    }

    @NotNull
    public  List<Task> listByProjectId(@NotNull final Long projectID) {
        return new ArrayList<>(); //check
    }

    @NotNull
    public  List<Task> list(@NotNull final String order) {
        final List<Task> taskList = findAll();
        taskList.sort(TaskSort.valueOf(order).getTaskComparator());
        return taskList;
    }

    @NotNull
    public Task findByID(@NotNull final Long taskID) {
        return taskRepository.findById(taskID).orElseThrow(() ->
                new RepositoryException("findByID", "TaskRepository"));
    }

    public void remove(@NotNull final Long taskID) {
        taskRepository.deleteById(taskID);
    }

    public void clear() {
        taskRepository.deleteAll();
    }

    @NotNull
    public List<Task> taskSearch(@NotNull final String word) {
        return taskRepository.findAllByTaskNameContains(word);
    }

    @NotNull
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public void update(@NotNull final Task task) {
        taskRepository.update(task.getTaskName(), task.getTaskDescription(), task.getStartDate(),
                task.getFinishDate(), task.getTaskStatus(), task.getTaskID());
    }
}
