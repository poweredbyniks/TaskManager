package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.niks.enums.TaskSort;
import org.niks.entity.Task;
import org.niks.exception.RepositoryException;
import org.niks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public @NotNull List<Task> list() {
        return taskList();
    }

    public @NotNull List<Task> list(@NotNull final String order) {
        final List<Task> taskList = taskList();
        taskList.sort(TaskSort.valueOf(order).getTaskComparator());
        return taskList;
    }

    @NotNull
    public Task findByID(@NotNull final Long taskID) {
        return taskRepository.findById(taskID).orElseThrow(() ->
                new RepositoryException("findByID" , "TaskRepository"));
    }

    public @NotNull List<Task> list(final long taskID) {
        return taskRepository.findAllByProject(taskID);
    }

    public void remove(@NotNull final Long taskID) {
        taskRepository.deleteById(taskID);
    }

    public void clear() {
        taskRepository.deleteAll();
    }

    @NotNull
    public List<Task> taskSearch(@NotNull final String word) {
        return taskRepository.findAllByTaskNameContainingAndTaskDescriptionContaining(word);
    }

    @NotNull
    public List<Task> taskList() {
        return taskRepository.findAll();
    }

    public void update(@NotNull final Task task) {
        taskRepository.update(task.getTaskName(), task.getTaskDescription(), task.getStartDate(),
                task.getFinishDate(), task.getTaskStatus(), task.getTaskID());
    }
}
