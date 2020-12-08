package org.niks.service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.repository.ITaskRepository;

import java.text.SimpleDateFormat;

@AllArgsConstructor
public final class TaskService implements ITaskService {
    private final ITaskRepository iTaskRepository;

    public void create(@NotNull final Task task) {
        if (!task.getTaskName().equals("")) {
            if (iTaskRepository.save(task)) {
                System.out.println("Task " + task.getTaskName() + " created and added to the project " + task.getProjectName());
            } else {
                System.out.println("No such existing project");
            }
        } else {
            System.out.println("Enter valid task name and try again");
        }
    }

    public void list() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Task task : iTaskRepository.findAll()) {
            System.out.println("Task " + task.getTaskName() + " in the project " + task.getProjectName() +
                    "\nStart date: " + dateFormat.format(task.getStartDate()) +
                    "\nFinish date: " + dateFormat.format(task.getFinishDate()));
        }
    }

    public void remove(@NotNull final String taskToRemove) {
        iTaskRepository.remove(taskToRemove);
        System.out.println("Task " + taskToRemove + "removed");
    }

    public void clear() {
        iTaskRepository.removeAll();
        System.out.println("Task list is empty");
    }
}
